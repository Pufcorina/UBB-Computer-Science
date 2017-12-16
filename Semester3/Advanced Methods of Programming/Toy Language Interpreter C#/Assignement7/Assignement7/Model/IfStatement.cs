using Assignement7.Collection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class IfStatement : IStatement
    {
        private Expression expression;
        private IStatement thenStatement;
        private IStatement elseStatement;

        public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement)
        {
            this.expression = expression;
            this.thenStatement = thenStatement;
            this.elseStatement = elseStatement;
        }

        public ProgramState Execute(ProgramState state)
        {
            MyIStack<IStatement> stack = state.getExecutionStack();
            int value = 0;
            value = expression.Evaluate(state.getSymbolTable());
            if (value != 0) stack.Push(thenStatement);
            else stack.Push(elseStatement);
            return state;
        }

        public override string ToString()
        {
            return "if(" + expression.ToString() + ") then " + thenStatement.ToString() + " else " + elseStatement.ToString();
        }
    }
}
