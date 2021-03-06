CREATE TABLE IF NOT EXISTS receipts (
  id INT UNSIGNED AUTO_INCREMENT,
  uploaded TIME DEFAULT CURRENT_TIME(),
  merchant VARCHAR(255),
  amount DECIMAL(12,2),
  receipt_type INT UNSIGNED,

  PRIMARY KEY (id)
);

CREATE TABLE tags (
  id INT UNSIGNED NOT NULL,
  created TIME DEFAULT CURRENT_TIME(),
  tag_name VARCHAR(255) NOT NULL,


  FOREIGN KEY (id)
  REFERENCES receipts(id)
    ON DELETE CASCADE
);