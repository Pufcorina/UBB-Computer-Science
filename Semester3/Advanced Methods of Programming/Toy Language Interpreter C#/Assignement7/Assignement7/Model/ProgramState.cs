using Assignement7.Collection;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class ProgramState
    {
        private MyIStack<IStatement> executionStack;
        private MyIDictionary<string, int> symbolTable;
        private MyIList<int> outputList;
        private MyIDictionary<int, Tuple<string, TextReader>> fileTable;

        public ProgramState(IStatement statement)
        {
            executionStack = new MyStack<IStatement>(new Stack<IStatement>());
            executionStack.Push(statement);
            symbolTable = new MyDictionary<string, int>(new Dictionary<string, int>());
            outputList = new MyList<int>(new List<int>());
            fileTable = new MyDictionary<int, Tuple<string, TextReader>>(new Dictionary<int, Tuple<string, TextReader>>());
        }

        public MyIDictionary<string, int> getSymbolTable()
        {
            return this.symbolTable;
        }

        public MyIDictionary<int, Tuple<string, TextReader>> getFileTable()
        {
            return this.fileTable;
        }

        public MyIList<int> getOutputList()
        {
            return this.outputList;
        }

        public MyIStack<IStatement> getExecutionStack()
        {
            return this.executionStack;
        }
        public override string ToString()
        {
            string ret = "";
            ret += "+++++++++++++PrgState+++++++++++++\n";
            ret += "-------------ExeStack-------------\n";
            ret += this.getExecutionStack().ToString() + "\n";
            ret += "-------------SymTable-------------\n";
            ret += this.getSymbolTable().ToString() + "\n";
            ret += "--------------Output--------------\n";
            ret += this.getOutputList().ToString() + "\n";
            ret += "\n";
            return ret;
        }
    }
}
