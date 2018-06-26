using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;
using System.Windows.Forms;

namespace Practic927
{
    public partial class Form1 : Form
    {
        private SqlConnection connection = new SqlConnection(GetConnectionString());

        private static string GetConnectionString()
        {
            return "Data Source=LAPTOP-MCH57F0B\\SQLEXPRESS;" +
                   "Initial Catalog=CarTraffic;" +
                   "Integrated Security = true;";
        }

        private SqlDataAdapter driverTable;
        private DataSet dataSetDrivers;
        private SqlCommandBuilder cmdbl;

        public Form1()
        {
            InitializeComponent();
            dataGridViewRoads.SelectionChanged += new EventHandler(LoadChildren);
            LoadParent();
        }

        private void LoadParent()
        {
            SqlDataAdapter roadCollectionTable = new SqlDataAdapter("select * from Roads", GetConnectionString());
            DataSet dataSet = new DataSet();

            roadCollectionTable.Fill(dataSet, "Roads");
            dataGridViewRoads.DataSource = dataSet.Tables["Roads"];
        }

        private void LoadChildren(object sender, EventArgs e)
        {
            LoadChildren();
        }

        private void LoadChildren()
        {
            int driverCollectionId = Convert.ToInt32(dataGridViewRoads.CurrentRow.Cells[0].Value);

            SqlCommand command = new SqlCommand("SELECT * FROM Drivers WHERE RoadId=@id")
            {
                Connection = new SqlConnection(GetConnectionString())
            };
            command.Parameters.AddWithValue("@id", driverCollectionId);

            driverTable = new SqlDataAdapter(command);
            dataSetDrivers = new DataSet();

            driverTable.Fill(dataSetDrivers, "Drivers");
            dataGridViewDrivers.DataSource = dataSetDrivers.Tables["Drivers"];
        }

        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            try
            {
                cmdbl = new SqlCommandBuilder(driverTable);
                driverTable.Update(dataSetDrivers, "Drivers");
                dataGridViewDrivers.Refresh();
                MessageBox.Show("Succes!");
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error");
            }
        }
    }
}
