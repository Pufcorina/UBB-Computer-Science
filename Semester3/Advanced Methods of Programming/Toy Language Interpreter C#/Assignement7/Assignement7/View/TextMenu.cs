using Assignement7.Collection;
using Assignement7.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.View
{
    class TextMenu
    {
        private MyIDictionary<string, Command> cmds;

        public TextMenu(MyIDictionary<string, Command> cmds) => this.cmds = cmds;

        public void AddCommand(Command cmd)
        {
            this.cmds.Put(cmd.GetKey(), cmd);
        }

        public void printMenu()
        {
            Console.WriteLine("Available commands: ");
            foreach (Command cmd in this.cmds.Values())
            {
                String line = String.Format("Command {0}: {1}", cmd.GetKey(), cmd.GetDescription());
                Console.WriteLine(line);
            }
        }

        public List<string> getCommandList()
        {
            List<string> l = new List<String>();
            foreach (Command cmd in this.cmds.Values())
                l.Add(cmd.GetDescription());
            return l;
        }

        public void show()
        {
            while (true)
            {
                printMenu();
                Console.WriteLine("Input the command: ");
                Command cmd = cmds.Get(Console.ReadLine());
                if (cmd == null)
                {
                    Console.WriteLine("Invalid command");
                    continue;
                }
                cmd.Execute();
            }
        }
    }
}
