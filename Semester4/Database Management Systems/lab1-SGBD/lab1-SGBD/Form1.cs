using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab1_SGBD
{
    public partial class Form1 : Form
    {
        private SqlConnection connection = new SqlConnection(GetConnectionString());
        private SqlDataAdapter bookTable;
        private DataSet dataSetBooks;
        private SqlCommandBuilder cmdbl;

        public Form1()
        {
            InitializeComponent();
            //VerifyConnection();
            dataGridViewBookCollections.SelectionChanged += new EventHandler(LoadChildren);
            LoadParent();
        }

        private void LoadParent()
        {
            SqlDataAdapter bookCollectionTable = new SqlDataAdapter("select * from BookCollections", GetConnectionString());
            DataSet dataSet = new DataSet();

            bookCollectionTable.Fill(dataSet, "BookCollections");
            dataGridViewBookCollections.DataSource = dataSet.Tables["BookCollections"];
        }

        private void LoadChildren(object sender, EventArgs e)
        {
            LoadChildren();
        }

        private void LoadChildren()
        {
            int bookCollectionId = Convert.ToInt32(dataGridViewBookCollections.CurrentRow.Cells[0].Value);

            SqlCommand command = new SqlCommand("SELECT * FROM Books WHERE BCId=@id")
            {
                Connection = new SqlConnection(GetConnectionString())
            };
            command.Parameters.AddWithValue("@id", bookCollectionId);

            bookTable = new SqlDataAdapter(command);
            dataSetBooks = new DataSet();

            bookTable.Fill(dataSetBooks, "Books");
            dataGridViewBooks.DataSource = dataSetBooks.Tables["Books"];
        }

        private static String GetConnectionString()
        {
            return "Data Source=LAPTOP-MCH57F0B\\SQLEXPRESS;" +
                   "Initial Catalog=BookLibrary;" +
                   "Integrated Security = true;";
        }

        private void ButtonView_Click(object sender, EventArgs e)
        {
            bookTable = new SqlDataAdapter("select * from Books", GetConnectionString());
            dataSetBooks = new DataSet();

            bookTable.Fill(dataSetBooks, "Books");
            dataGridViewBooks.DataSource = dataSetBooks.Tables["Books"];
        }

        private void ButtonUpdate_Click(object sender, EventArgs e)
        {
            try
            {
                cmdbl = new SqlCommandBuilder(bookTable);
                bookTable.Update(dataSetBooks, "Books");
                MessageBox.Show("Succes!");
            } catch(Exception ex){
                MessageBox.Show("Error");
            }
        }

        private static void VerifyConnection()
        {
            SqlConnection conn = new SqlConnection(GetConnectionString());
            SqlDataReader reader = null;
            try
            {
                conn.Open();
                Console.WriteLine("Connection Open!");
                SqlCommand command = new SqlCommand("select * from Books", conn);
                reader = command.ExecuteReader();
                reader.Read();
                MessageBox.Show(reader[0].ToString());

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                if (reader != null)
                {
                    reader.Close();
                }

                conn.Close();
            }
        }

    }
}
