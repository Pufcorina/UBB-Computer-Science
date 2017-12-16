using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class CloseStatement : IStatement
    {
        private Expression expression;

        public CloseStatement(Expression expression)
        {
            this.expression = expression;
        }

        public override string ToString()
        {
            return " closeRFile(" + this.expression.ToString() + ")";
        }

        public ProgramState Execute(ProgramState state)
        {
            int id = this.expression.Evaluate(state.getSymbolTable());
            Tuple<string, TextReader> pair = state.getFileTable().Get(id);

            if (pair == null)
                throw new Exception("FileNotOpened Exception at: " + this.ToString() + "\nThere is no opened file with id = " + id);
            pair.Item2.Close();
            state.getFileTable().Remove(id);
            return state;
        }
    }
}
