using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Collection
{
    public interface MyIStack<T>
    {
        void Push(T element);
        T Pop();
        T Peek();
        bool IsEmpty();
        Stack<T> ToStack();
        string ToString();
    }
}
