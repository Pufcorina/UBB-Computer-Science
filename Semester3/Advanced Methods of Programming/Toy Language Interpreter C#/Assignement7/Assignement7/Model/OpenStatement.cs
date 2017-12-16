using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignement7.Model
{
    public class OpenStatement : IStatement
    {
        private string var_id;
        private string fileName;
        private static int uniq = 1;


        public OpenStatement(String var_id, String fileName)
        {
            this.var_id = var_id;
            this.fileName = fileName;
        }

        public override string ToString()
        {
            return "openRFile(" + var_id + ",\"" + fileName + "\")";
        }


        public ProgramState Execute(ProgramState state)
        {
            var fileTable = state.getFileTable();
            foreach (Tuple<string, TextReader> pair in fileTable.Values())
                if (pair.Item1 == this.fileName)
                    throw new Exception("File already opened");
            TextReader t = File.OpenText(this.fileName);
            int id = ++OpenStatement.uniq;
            state.getFileTable().Put(id, new Tuple<string, TextReader>(this.fileName, t));
            state.getSymbolTable().Put(var_id, id);
            return state;
        }
    }
}
