using Assignement7.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Repository_folder
{
    public interface IRepository
    {
        List<ProgramState> GetPrgList();
        void LogPrgStateExec(ProgramState state);
        void SetPrgList(List<ProgramState> prgList);
    }
}
