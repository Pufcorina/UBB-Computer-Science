using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class PrintStatement : IStatement
    {
        Expression expression;

        public PrintStatement(Expression expression)
        {
            this.expression = expression;
        }


        public override string ToString()
        {
            return "print( " + expression.ToString() + " )";
        }


        public ProgramState Execute(ProgramState state)
        {
            state.getOutputList().Add(expression.Evaluate(state.getSymbolTable()));
            return state;
        }
    }
}
