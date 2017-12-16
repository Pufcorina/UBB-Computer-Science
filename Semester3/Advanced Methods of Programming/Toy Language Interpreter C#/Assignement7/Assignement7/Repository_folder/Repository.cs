using Assignement7.Model;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Repository_folder
{
    public class Repository : IRepository
    {
        private List<ProgramState> list;
        string log;
        public Repository(string log)
        {
            this.list = new List<ProgramState>();
            this.log = log;
        }
        public List<ProgramState> GetPrgList()
        {
            return this.list;
        }

        public void LogPrgStateExec(ProgramState state)
        {
            File.WriteAllText(this.log, state.ToString());
            Console.WriteLine(state.ToString());
        }

        public void SetPrgList(List<ProgramState> prgList)
        {
            this.list = prgList;
        }
    }
}
