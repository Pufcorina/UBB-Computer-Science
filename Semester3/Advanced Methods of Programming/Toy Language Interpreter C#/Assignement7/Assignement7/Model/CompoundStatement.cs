using Assignement7.Collection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class CompoundStatement : IStatement
    {
        private IStatement first;
        private IStatement second;

        public CompoundStatement(IStatement first, IStatement second)
        {
            this.first = first;
            this.second = second;
        }


        public ProgramState Execute(ProgramState state)
        {
            MyIStack<IStatement> stack = state.getExecutionStack();
            stack.Push(second);
            stack.Push(first);
            return null;
        }

        public override string ToString()
        {
            return first.ToString() + "; " + second.ToString();
        }
    }
}
