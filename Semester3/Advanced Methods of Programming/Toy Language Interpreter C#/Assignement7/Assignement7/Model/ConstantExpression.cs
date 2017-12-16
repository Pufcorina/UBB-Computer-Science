using Assignement7.Collection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class ConstantExpression : Expression
    {
        private int value;

        public ConstantExpression(int val)
        {
            this.value = val;
        }

        public override int Evaluate(MyIDictionary<string, int> symbolTable)
        {
            return value;
        }

        public override string ToString()
        {
            return value.ToString();
        }
    }
}
