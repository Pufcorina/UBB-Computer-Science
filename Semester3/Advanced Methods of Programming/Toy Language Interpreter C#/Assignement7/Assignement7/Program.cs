using Assignement7.Collection;
using Assignement7.Controller_folder;
using Assignement7.Model;
using Assignement7.Repository_folder;
using Assignement7.View;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static Assignement7.Model.ArithmeticExpression;

namespace Assignement7
{
    class Program
    {
        static void Main(string[] args)
        {
            IStatement  ex1 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(2)),
                new PrintStatement(new VariableExpression("v")));
           
            IStatement ex2 = new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ConstantExpression(2), new
                    ArithmeticExpression(new ConstantExpression(3), new ConstantExpression(5), Operation.MULTIPLY), Operation.ADD)),
                    new CompoundStatement(new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new
                            ConstantExpression(1), Operation.ADD)), new PrintStatement(new VariableExpression("b"))));
            
            IStatement ex3 = new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ConstantExpression(2), new ConstantExpression(2), Operation.SUBTRACT)),
                    new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ConstantExpression(2)),
                            new AssignStatement("v", new ConstantExpression(3))), new PrintStatement(new VariableExpression("v"))));
            
            IStatement ex4 = new CompoundStatement(new OpenStatement("var_f", "test1.in"),
                    new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                            new CompoundStatement(new PrintStatement(new VariableExpression("var_c")),
                                    new CompoundStatement(new IfStatement(new VariableExpression("var_c"),
                                            new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                                                    new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new ConstantExpression(0))),
                                            new CloseStatement(new VariableExpression("var_f"))))));

            TextMenu menu = new TextMenu(new MyDictionary<string, Command>(new Dictionary<string, Command>()));

            menu.AddCommand(new ExitCommand("0", "exit"));
            menu.AddCommand(new RunCommand("1", ex1.ToString(), CreateController(ex1, "log1.txt")));
            menu.AddCommand(new RunCommand("2", ex2.ToString(), CreateController(ex2, "log2.txt")));
            menu.AddCommand(new RunCommand("3", ex3.ToString(), CreateController(ex3, "log3.txt")));
            menu.AddCommand(new RunCommand("4", ex4.ToString(), CreateController(ex4, "log4.txt")));

            menu.show();
        }

        static Controller CreateController(IStatement stmt, string log)
        {
            if (File.Exists(log))
            {
                File.Delete(log);
            }
            IRepository repo = new Repository(log);
            Controller ctrl = new Controller(repo);
            ctrl.SetMain(new ProgramState(stmt));
            return ctrl;
        }
    }
}
