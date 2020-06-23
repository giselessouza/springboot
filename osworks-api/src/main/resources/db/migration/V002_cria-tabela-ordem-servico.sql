CREATE ordem_servico {
id bigint NOT NULL auto_increment,
client_id bigint NOT NULL,
descricao text NOT NULL,
preco decimal(10,2) NOT NULL,
status varchar(20) NOT NULL,
data_abertura datetime NOT NULL,
data_finalizcao datetime,

PRIMARY KEY (id)
);

ALTER TABLE ordem_servico ADD CONSTRAINT fk_ordem_servico_cliente
FOREIGN KEY (cliente_id) REFERENCES cliente (id);
