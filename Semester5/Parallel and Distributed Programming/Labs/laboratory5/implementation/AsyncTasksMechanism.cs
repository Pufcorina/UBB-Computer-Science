using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using Lab5.model;
using Lab5.util;

namespace Lab5.implementation {
    public static class AsyncTaskMechanism {
        private static List<string> HOSTS;

        public static void run(List<string> hostnames) {
            HOSTS = hostnames;

            var tasks = new List<Task>();

            for (var i = 0; i < HOSTS.Count; i++) {
                tasks.Add(Task.Factory.StartNew(doStart, i));
            }

            Task.WaitAll(tasks.ToArray());
        }

        private static void doStart(object idObject) {
            var id = (int) idObject;

            StartClient(HOSTS[id], id);
        }

        /**
         * Connect to a server.
         */
        private static async void StartClient(string host, int id) {
            // establish the remote endpoint of the server  
            var ipHostInfo = Dns.GetHostEntry(host.Split('/')[0]);
            var ipAddress = ipHostInfo.AddressList[0];
            var remoteEndpoint = new IPEndPoint(ipAddress, HttpUtils.HTTP_PORT);

            // create the TCP/IP socket
            var client = new Socket(ipAddress.AddressFamily, SocketType.Stream, ProtocolType.Tcp);

            // create a wrapper for the connection information
            var state = new StateObject {
                socket = client,
                hostname = host.Split('/')[0],
                endpoint = host.Contains("/") ? host.Substring(host.IndexOf("/")) : "/",
                remoteEndpoint = remoteEndpoint,
                id = id
            };

            // connect to the remote endpoint  
            await ConnectWrapper(state);

            // request data from the server
            await SendWrapper(state, HttpUtils.getRequestString(state.hostname, state.endpoint));

            // receive the response from the server
            await ReceiveWrapper(state);

            // write the response details to the console
            Console.WriteLine(
                "{0} --> Response received : expected {1} chars in body, got {2} chars (headers + body)", 
                id, HttpUtils.getContentLength(state.responseContent.ToString()), state.responseContent.Length);

            // release the socket
            client.Shutdown(SocketShutdown.Both);
            client.Close();
        }

        private static async Task ConnectWrapper(StateObject state) {
            state.socket.BeginConnect(state.remoteEndpoint, ConnectCallback, state);

            await Task.FromResult<object>(state.connectDone.WaitOne());
        }

        private static void ConnectCallback(IAsyncResult ar) {
            // retrieve the details from the connection information wrapper
            var state = (StateObject) ar.AsyncState;
            var clientSocket = state.socket;
            var clientId = state.id;
            var hostname = state.hostname;

            // complete the connection  
            clientSocket.EndConnect(ar);

            Console.WriteLine("{0} --> Socket connected to {1} ({2})", clientId, hostname, clientSocket.RemoteEndPoint);

            // signal that the connection has been made 
            state.connectDone.Set();
        }

        private static async Task SendWrapper(StateObject state, string data) {
            // convert the string data to byte data using ASCII encoding.  
            var byteData = Encoding.ASCII.GetBytes(data);

            // begin sending the data to the server  
            state.socket.BeginSend(byteData, 0, byteData.Length, 0, SendCallback, state);

            await Task.FromResult<object>(state.sendDone.WaitOne());
        }

        private static void SendCallback(IAsyncResult ar) {
            var state = (StateObject) ar.AsyncState;
            var clientSocket = state.socket;
            var clientId = state.id;

            // complete sending the data to the server  
            var bytesSent = clientSocket.EndSend(ar);
            Console.WriteLine("{0} --> Sent {1} bytes to server.", clientId, bytesSent);

            // signal that all bytes have been sent
            state.sendDone.Set();
        }

        private static async Task ReceiveWrapper(StateObject state) {
            // begin receiving the data from the server
            state.socket.BeginReceive(state.buffer, 0, StateObject.BUFFER_SIZE, 0, ReceiveCallback, state);

            await Task.FromResult<object>(state.receiveDone.WaitOne());
        }

        private static void ReceiveCallback(IAsyncResult ar) {
            // retrieve the details from the connection information wrapper
            var state = (StateObject) ar.AsyncState;
            var clientSocket = state.socket;

            try {
                // read data from the remote device.  
                var bytesRead = clientSocket.EndReceive(ar);

                // get from the buffer, a number of characters <= to the buffer size, and store it in the responseContent
                state.responseContent.Append(Encoding.ASCII.GetString(state.buffer, 0, bytesRead));

                // if the response header has not been fully obtained, get the next chunk of data
                if (!HttpUtils.responseHeaderFullyObtained(state.responseContent.ToString())) {
                    clientSocket.BeginReceive(state.buffer, 0, StateObject.BUFFER_SIZE, 0, ReceiveCallback, state);
                } else {
                    // header has been fully obtained
                    // get the body
                    var responseBody = HttpUtils.getResponseBody(state.responseContent.ToString());

                    // the custom header parser is being used to check if the data received so far has the length
                    // specified in the response headers
                    if (responseBody.Length < HttpUtils.getContentLength(state.responseContent.ToString())) {
                        // if it isn't, than more data is to be retrieve
                        clientSocket.BeginReceive(state.buffer, 0, StateObject.BUFFER_SIZE, 0, ReceiveCallback, state);
                    } else {
                        // otherwise, all the data has been received  
                        // signal that all bytes have been received  
                        state.receiveDone.Set();
                    }
                }
            } catch (Exception e) {
                Console.WriteLine(e.ToString());
            }
        }
    }
}
