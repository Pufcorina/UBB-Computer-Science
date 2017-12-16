using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class AssignStatement : IStatement
    {
        private string id;
        private Expression expression;

        public AssignStatement(string id, Expression expression)
        {
            this.id = id;
            this.expression = expression;
        }

        public ProgramState Execute(ProgramState state)
        {
            int value = this.expression.Evaluate(state.getSymbolTable());
            state.getSymbolTable().Put(id, value);
            return state;
        }

        public override string ToString()
        {
            return id + " = " + this.expression.ToString();
        }
    }
}
