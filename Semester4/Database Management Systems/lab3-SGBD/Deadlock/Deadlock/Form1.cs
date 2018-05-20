using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Deadlock
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void buttonStart_Click(object sender, EventArgs e)
        {
            ThreadStart deadlock1 = new ThreadStart(Deadlock1);
            ThreadStart deadlock2 = new ThreadStart(Deadlock2);

            Thread d1 = new Thread(deadlock1);
            Thread d2 = new Thread(deadlock2);

            d1.Start();
            d2.Start();
        }

        void Deadlock1()
        {
            SqlConnection connection = new SqlConnection("Data Source=LAPTOP-MCH57F0B\\SQLEXPRESS;Initial Catalog=BookLibrary;Integrated Security=True;");
            MessageBox.Show("Deadlock1 started!");
            SqlCommand command = new SqlCommand("Deadlock1", connection);
            command.CommandType = CommandType.StoredProcedure;
            connection.Open();
            int rows_affected = 0;
            try { 
                rows_affected = command.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Deadlock1 failed!");
                int tries = 1;
                while(rows_affected < 2 && tries > 0)
                {
                    Console.WriteLine("Deadlock1 failed! rows affected: " + rows_affected + " tries: " + tries);
                    try
                    {
                        rows_affected = command.ExecuteNonQuery();
                        Console.WriteLine("Deadlock1 tries: " + (1 - tries + 1));
                    }
                    catch (Exception exe)
                    {
                        Console.WriteLine("Deadlock1 failed!");
                    }
                }
            }
        }

        void Deadlock2()
        {
            SqlConnection connection = new SqlConnection("Data Source=LAPTOP-MCH57F0B\\SQLEXPRESS;Initial Catalog=BookLibrary;Integrated Security=True;");
            MessageBox.Show("Deadlock2 started!");
            SqlCommand command = new SqlCommand("Deadlock2", connection);
            command.CommandType = CommandType.StoredProcedure;
            connection.Open();
            int rows_affected = 0;
            try
            {
                rows_affected = command.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Deadlock2 failed!");
                int tries = 1;
                while (rows_affected < 2 && tries > 0)
                {
                    Console.WriteLine("Deadlock2 failed! rows affected: " + rows_affected + " tries: " + tries);
                    try
                    {
                        rows_affected = command.ExecuteNonQuery();
                        Console.WriteLine("Deadlock2 tries: " + (1 - tries + 1));
                    }
                    catch (Exception exe)
                    {
                        Console.WriteLine("Deadlock2 failed!");
                    }
                }
            }
        }

    }
}
