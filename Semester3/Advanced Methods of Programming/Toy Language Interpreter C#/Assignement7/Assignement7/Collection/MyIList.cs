using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Collection
{
    public interface MyIList<T>
    {
        void Add(T element);
        T Get(int index);
        bool Remove(T element);
        T Remove(int index);
        int Size();
        List<T> ToList();
        string ToString();
    }
}
