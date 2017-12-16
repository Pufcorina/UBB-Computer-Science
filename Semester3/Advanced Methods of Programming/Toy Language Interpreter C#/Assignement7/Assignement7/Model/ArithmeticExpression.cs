using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Assignement7.Collection;

namespace Assignement7.Model
{
    public class ArithmeticExpression : Expression
    {
        public enum Operation
        {
            ADD,
            SUBTRACT,
            MULTIPLY,
            DIVIDE
        }

        private Expression left;
        private Expression right;
        Operation op;
        
        public ArithmeticExpression(Expression left, Expression right, Operation op)
        {
            this.op = op;
            this.left = left;
            this.right = right;
        }

        public override int Evaluate(MyIDictionary<string, int> symbolTable)
        {
            int l = this.left.Evaluate(symbolTable);
            int r = this.right.Evaluate(symbolTable);

            int ret = l;
            switch(this.op)
            {
                case Operation.ADD:
                    ret += r;
                    break;
                case Operation.SUBTRACT:
                    ret -= r;
                    break;
                case Operation.MULTIPLY:
                    ret *= r;
                    break;
                case Operation.DIVIDE:
                    if (r == 0)
                        throw new Exception("Division by zero");
                    ret /= r;
                    break;
            }
            return ret;
        }

        public override string ToString()
        {
            string ret = this.left.ToString();

            switch (this.op)
            {
                case Operation.ADD:
                    ret += " + ";
                    break;
                case Operation.SUBTRACT:
                    ret += " - ";
                    break;
                case Operation.MULTIPLY:
                    ret += " * ";
                    break;
                case Operation.DIVIDE:
                    ret += " / ";
                    break;
            }

            ret += this.right.ToString();
            return ret;
        }
    }
}
