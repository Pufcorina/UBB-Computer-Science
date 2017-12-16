using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class ExitCommand : Command
    {
        public ExitCommand(string key, string description) : base(key, description)
        { }

        public override void Execute()
        {
            Console.WriteLine("Program terminated");
            Environment.Exit(0);
        }
    }
}
