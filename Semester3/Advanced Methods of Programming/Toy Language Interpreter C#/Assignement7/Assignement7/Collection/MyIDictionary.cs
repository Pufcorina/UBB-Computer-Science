using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Collection
{
    public interface MyIDictionary<K, V>
    {
        void Put(K key, V value);
        V Get(K key);
        List<V> Values();
        List<K> Keys();
        void Remove(K key);
        Dictionary<K, V> ToDictionary();
        MyIDictionary<K, V> Clone();
        string ToString();
    }
}
