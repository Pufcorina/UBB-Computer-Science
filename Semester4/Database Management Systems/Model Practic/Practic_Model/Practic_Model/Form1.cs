using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace Practic_Model
{
    public partial class Form1 : Form
    {
        private SqlConnection dbConn;
        private SqlDataAdapter daUsers, daPosts;
        private DataSet ds;
        private BindingSource bsUsers, bsPosts;
        private SqlCommandBuilder cmd;

        private String connectionString = "Data Source=LAPTOP-MCH57F0B\\SQLEXPRESS;Initial Catalog=MiniFacebook;Integrated Security=SSPI;";

        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            try
            {
                daPosts.Update(ds, "Posts");
                MessageBox.Show("Updated");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            dbConn = new SqlConnection(connectionString);
            ds = new DataSet();

            daUsers = new SqlDataAdapter("select * from Users", dbConn);
            daPosts = new SqlDataAdapter("select * from Posts", dbConn);

            daUsers.Fill(ds, "Users");
            daPosts.Fill(ds, "Posts");
            cmd = new SqlCommandBuilder(daPosts);

            ds.Relations.Add(new DataRelation("FK_Posts_Users", ds.Tables["Users"].Columns["UsID"], ds.Tables["Posts"].Columns["UsID"]));

            bsUsers = new BindingSource();
            bsUsers.DataSource = ds;
            bsUsers.DataMember = "Users";

            bsPosts = new BindingSource(bsUsers, "FK_Posts_Users");

            dgvUsers.DataSource = bsUsers;
            dgvPosts.DataSource = bsPosts;

        }
    }
}
