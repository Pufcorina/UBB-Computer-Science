using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Collection
{
    public class MyList<T> : MyIList<T>
    {
        private List<T> lst;

        public MyList(List<T> list)
        {
            this.lst = list;
        }

        public void Add(T element)
        {
            this.lst.Add(element);
        }

        public T Get(int index)
        {
            return this.lst.ElementAt(index);
        }

        public T Remove(int index)
        {
            T el = this.lst.ElementAt(index);
            this.lst.RemoveAt(index);
            return el;
        }

        public bool Remove(T element)
        {
            return this.lst.Remove(element);
        }

        public int Size()
        {
            return this.lst.Count;
        }

        public List<T> ToList()
        {
            return this.lst;
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();
            this.lst.ForEach(e => sb.Append(e.ToString() + "\n"));
            return sb.ToString();
        }
    }
}
