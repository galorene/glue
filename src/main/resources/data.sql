DROP TABLE IF EXISTS product;
CREATE TABLE IF NOT EXISTS product (
   id INT NOT NULL,
   name VARCHAR(50) NOT NULL,
   sales_units VARCHAR(50) NOT NULL,
   stocks VARCHAR(50) NOT NULL
);
DROP TABLE IF EXISTS criteria;
CREATE TABLE IF NOT EXISTS criteria (
   id INT NOT NULL,
   name_criteria VARCHAR(50) NOT NULL
);

INSERT INTO product (id,name,sales_units,stocks) VALUES (1, 'V-NECK BASIC SHIRT', 100, 'S:4 / M:9 / L:0');
INSERT INTO product (id,name,sales_units,stocks) VALUES (2, 'CONTRASTING FABRIC T-SHIRT', 50, 'S:35 / M:9 / L:9');
INSERT INTO product (id,name,sales_units,stocks) VALUES (3, 'RAISED PRINT T-SHIRT', 80, 'S:20 / M:2 / L20');
INSERT INTO product (id,name,sales_units,stocks) VALUES (4, 'PLEATED T-SHIRT ', 3, 'S:25 / M:30 / L:10');
INSERT INTO product (id,name,sales_units,stocks) VALUES (5, 'CONTRASTING LACE T-SHIRT', 650, 'S:0 / M:1 / L:0');
INSERT INTO product (id,name,sales_units,stocks) VALUES (6, 'SLOGAN T-SHIRT', 20, 'S:9 / M:2 / L:5');

INSERT INTO criteria (id,name_criteria) VALUES (1, 'unitSales');
INSERT INTO criteria (id,name_criteria) VALUES (2, 'stocks');