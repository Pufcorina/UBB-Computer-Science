using Assignement7.Collection;
using Assignement7.Model;
using Assignement7.Repository_folder;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Controller_folder
{
    public class Controller
    {

        private IRepository repo;
        public Controller(IRepository repo)
        {
            this.repo = repo;
        }

        public void SetMain(ProgramState prgState)
        {
            this.repo.GetPrgList().Clear();
            this.repo.GetPrgList().Add(prgState);
        }

        public ProgramState OneStep(ProgramState prgState)
        {
            MyIStack<IStatement> exeStack = prgState.getExecutionStack();
            if (exeStack.IsEmpty())
                throw new Exception("stack is empty");
            return exeStack.Pop().Execute(prgState);
        }

        public void AllSteps()
        {
            ProgramState prgState = this.repo.GetPrgList().ElementAt(0);
            repo.LogPrgStateExec(prgState);
            while (prgState.getExecutionStack().IsEmpty() == false)
            {
                OneStep(prgState);
                repo.LogPrgStateExec(prgState);
            }
        }
    }
}
