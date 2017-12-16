using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Assignement7.Controller_folder;

namespace Assignement7.Model
{
    public class RunCommand : Command
    {
        private Controller ctr;

        public RunCommand(string key, string desciption, Controller ctr) : base(key, desciption)
        {
            this.ctr = ctr;
        }

        public override void Execute()
        {
            try
            {
                ctr.AllSteps();
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
            }
        }
    }
}
