using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace Lab5.model {
    public class StateObject {
        // client socket
        public Socket socket = null;

        // size of receive buffer
        public const int BUFFER_SIZE = 512;

        // receive buffer  
        public byte[] buffer = new byte[BUFFER_SIZE];

        // received data  
        public StringBuilder responseContent = new StringBuilder();

        // client id
        public int id;

        // server's hostname
        public string hostname;

        // request path
        public string endpoint;
        
        // server's ip address
        public IPEndPoint remoteEndpoint;

        // mutex for "connect" operation
        public ManualResetEvent connectDone = new ManualResetEvent(false);

        // mutex for "send" operation
        public ManualResetEvent sendDone = new ManualResetEvent(false);

        // mutex for "receive" operation
        public ManualResetEvent receiveDone = new ManualResetEvent(false);
    }
}
