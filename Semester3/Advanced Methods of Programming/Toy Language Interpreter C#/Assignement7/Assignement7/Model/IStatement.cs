using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public interface IStatement
    {
        string ToString();
        ProgramState Execute(ProgramState state);

    }
}
