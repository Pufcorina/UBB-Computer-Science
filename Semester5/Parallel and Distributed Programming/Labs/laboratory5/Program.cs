using System.Collections.Generic;
using Lab5.implementation;

namespace Lab5 {
    public static class Program {
        // adding 3 hosts, each returning a response in different format:
        private static readonly List<string> HOSTS = new List<string> {
            // - gzip form (compressed)
            "www.cs.ubbcluj.ro/~rlupsa/edu/pdp",
            // - empty body (just signals that the page has moved and the HTTPS protocol should be used from now on)
            "facebook.com",
            // - plain text
            "google.com", 
        };

        public static void Main(string[] args) {
            //DirectCallbacks.run(HOSTS);
            //TaskMechanism.run(HOSTS);
            AsyncTaskMechanism.run(HOSTS);
        }
    }
}
