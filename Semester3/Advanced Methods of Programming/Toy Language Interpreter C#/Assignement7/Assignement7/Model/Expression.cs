using Assignement7.Collection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public abstract class Expression
    {
        public abstract int Evaluate(MyIDictionary<string, int> symbolTable);
        public abstract override string ToString();
    }
}
