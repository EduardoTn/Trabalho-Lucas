-- MySQL Script generated by MySQL Workbench
-- Sun Jun  9 21:11:25 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema loja
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema loja
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `loja` DEFAULT CHARACTER SET utf8 ;
USE `loja` ;

-- -----------------------------------------------------
-- Table `loja`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `NumeroContato` BIGINT(20) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `NomeProduto` VARCHAR(45) NOT NULL,
  `TipoProduto` VARCHAR(45) NOT NULL,
  `Peso` FLOAT NOT NULL,
  `QuantEstoque` INT NOT NULL,
  `Preco` FLOAT NOT NULL,
  PRIMARY KEY (`idProduto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja`.`Compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`Compra` (
  `idCompra` INT NOT NULL AUTO_INCREMENT,
  `tipoPedido` VARCHAR(45) NOT NULL,
  `InfoPagamento` VARCHAR(45) NOT NULL,
  `PrecoTotal` FLOAT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idCompra`),
  INDEX `fk_Compra_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Compra_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `loja`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  INDEX `fk_Funcionario_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionario_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `loja`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`Administrador` (
  `idAdministrador` INT NOT NULL AUTO_INCREMENT,
  `Funcionario_idFuncionario` INT NOT NULL,
  PRIMARY KEY (`idAdministrador`),
  INDEX `fk_Administrador_Funcionario1_idx` (`Funcionario_idFuncionario` ASC) VISIBLE,
  CONSTRAINT `fk_Administrador_Funcionario1`
    FOREIGN KEY (`Funcionario_idFuncionario`)
    REFERENCES `loja`.`Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja`.`Produto_has_Compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`Produto_has_Compra` (
  `Produto_idProduto` INT NOT NULL,
  `Compra_idCompra` INT NOT NULL,
  `Quant` INT NOT NULL,
  PRIMARY KEY (`Produto_idProduto`, `Compra_idCompra`),
  INDEX `fk_Produto_has_Compra_Compra1_idx` (`Compra_idCompra` ASC) VISIBLE,
  INDEX `fk_Produto_has_Compra_Produto1_idx` (`Produto_idProduto` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_has_Compra_Produto1`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `loja`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_has_Compra_Compra1`
    FOREIGN KEY (`Compra_idCompra`)
    REFERENCES `loja`.`Compra` (`idCompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Create Trigger Baixa de estoque
-- -----------------------------------------------------

Delimiter $

CREATE TRIGGER BaixaEstoque AFTER INSERT ON produto_has_compra
FOR EACH ROW
BEGIN
	UPDATE produto set QuantEstoque = QuantEstoque - new.Quant 
    where produto.idProduto = new.Produto_idProduto;
end$
Delimiter ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;