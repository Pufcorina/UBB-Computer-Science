using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class ReadStatement : IStatement
    {
        private Expression var;
        private String var_name;

        public ReadStatement(Expression var, String var_name)
        {
            this.var = var;
            this.var_name = var_name;
        }

        public override string ToString()
        {
            return "readFile(" + var.ToString() + ", " + var_name + ")";
        }


        public ProgramState Execute(ProgramState state)
        {
            int id = this.var.Evaluate(state.getSymbolTable());
            Tuple<string, TextReader> pair = state.getFileTable().Get(id);

            if(pair == null)
                throw new Exception("FileNotOpenedException at: " + this.ToString() + "\nNo such file descriptor: " + id.ToString());

            string line = pair.Item2.ReadLine();
            int val = 0;
            if (line != null)
                val = int.Parse(line);
            state.getSymbolTable().Put(this.var_name, val);
            return state;
        }
    }
}
