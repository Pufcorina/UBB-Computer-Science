using Assignement7.Collection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class VariableExpression : Expression
    {
        private String name;

        public VariableExpression(String name)
        {
            this.name = name;
        }

        public override int Evaluate(MyIDictionary<string, int> symbolTable)
        {
            return symbolTable.Get(name);
        }

        public override string ToString()
        {
            return name;
        }
    }
}
