using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Collection
{
    public class MyDictionary<K, V> : MyIDictionary<K, V>
    {
        private Dictionary<K, V> map;

        public MyDictionary(Dictionary<K, V> map)
        {
            this.map = map;
        }

        public MyIDictionary<K, V> Clone()
        {
            return new MyDictionary<K, V>(new Dictionary<K, V>(this.map));
        }

        public V Get(K key)
        {
            if (this.map.ContainsKey(key))
                return this.map[key];
            else
                throw new Exception("Key not exist in table");
        }

        public List<K> Keys()
        {
            return new List<K>(map.Keys);
        }

        public List<V> Values()
        {
            return new List<V>(map.Values);
        }

        public void Put(K key, V value)
        {
            this.map[key] = value;
        }

        public void Remove(K key)
        {
            this.map.Remove(key);
        }

        public Dictionary<K, V> ToDictionary()
        {
            return this.map;
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();
            this.Keys().ForEach(e => sb.Append(e.ToString() + " -> " + this.Get(e).ToString() + "\n"));
            return sb.ToString();
        }
    }
}
