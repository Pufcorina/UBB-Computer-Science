namespace lab1_SGBD
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridViewBooks = new System.Windows.Forms.DataGridView();
            this.dataGridViewBookCollections = new System.Windows.Forms.DataGridView();
            this.labelBookCollections = new System.Windows.Forms.Label();
            this.labelBooks = new System.Windows.Forms.Label();
            this.buttonView = new System.Windows.Forms.Button();
            this.buttonUpdate = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewBooks)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewBookCollections)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewBooks
            // 
            this.dataGridViewBooks.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.ColumnHeader;
            this.dataGridViewBooks.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewBooks.Location = new System.Drawing.Point(12, 64);
            this.dataGridViewBooks.Name = "dataGridViewBooks";
            this.dataGridViewBooks.RowTemplate.Height = 24;
            this.dataGridViewBooks.Size = new System.Drawing.Size(920, 652);
            this.dataGridViewBooks.TabIndex = 0;
            // 
            // dataGridViewBookCollections
            // 
            this.dataGridViewBookCollections.AllowUserToAddRows = false;
            this.dataGridViewBookCollections.AllowUserToDeleteRows = false;
            this.dataGridViewBookCollections.AllowUserToResizeColumns = false;
            this.dataGridViewBookCollections.AllowUserToResizeRows = false;
            this.dataGridViewBookCollections.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dataGridViewBookCollections.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewBookCollections.Location = new System.Drawing.Point(954, 64);
            this.dataGridViewBookCollections.Name = "dataGridViewBookCollections";
            this.dataGridViewBookCollections.RowTemplate.Height = 24;
            this.dataGridViewBookCollections.Size = new System.Drawing.Size(393, 652);
            this.dataGridViewBookCollections.TabIndex = 1;
            // 
            // labelBookCollections
            // 
            this.labelBookCollections.AutoSize = true;
            this.labelBookCollections.Location = new System.Drawing.Point(1073, 28);
            this.labelBookCollections.Name = "labelBookCollections";
            this.labelBookCollections.Size = new System.Drawing.Size(168, 17);
            this.labelBookCollections.TabIndex = 2;
            this.labelBookCollections.Text = "Book Collections (Parent)";
            // 
            // labelBooks
            // 
            this.labelBooks.AutoSize = true;
            this.labelBooks.Location = new System.Drawing.Point(428, 28);
            this.labelBooks.Name = "labelBooks";
            this.labelBooks.Size = new System.Drawing.Size(92, 17);
            this.labelBooks.TabIndex = 3;
            this.labelBooks.Text = "Books (Child)";
            // 
            // buttonView
            // 
            this.buttonView.Location = new System.Drawing.Point(64, 18);
            this.buttonView.Name = "buttonView";
            this.buttonView.Size = new System.Drawing.Size(180, 37);
            this.buttonView.TabIndex = 4;
            this.buttonView.Text = "View all Books table";
            this.buttonView.UseVisualStyleBackColor = true;
            this.buttonView.Click += new System.EventHandler(this.ButtonView_Click);
            // 
            // buttonUpdate
            // 
            this.buttonUpdate.Location = new System.Drawing.Point(731, 18);
            this.buttonUpdate.Name = "buttonUpdate";
            this.buttonUpdate.Size = new System.Drawing.Size(120, 36);
            this.buttonUpdate.TabIndex = 5;
            this.buttonUpdate.Text = "Update";
            this.buttonUpdate.UseVisualStyleBackColor = true;
            this.buttonUpdate.Click += new System.EventHandler(this.ButtonUpdate_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1359, 728);
            this.Controls.Add(this.buttonUpdate);
            this.Controls.Add(this.buttonView);
            this.Controls.Add(this.labelBooks);
            this.Controls.Add(this.labelBookCollections);
            this.Controls.Add(this.dataGridViewBookCollections);
            this.Controls.Add(this.dataGridViewBooks);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewBooks)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewBookCollections)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewBooks;
        private System.Windows.Forms.DataGridView dataGridViewBookCollections;
        private System.Windows.Forms.Label labelBookCollections;
        private System.Windows.Forms.Label labelBooks;
        private System.Windows.Forms.Button buttonView;
        private System.Windows.Forms.Button buttonUpdate;
    }
}

