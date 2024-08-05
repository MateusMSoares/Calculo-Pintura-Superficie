class Tinta{
    constructor(nome, preco){
        this.nome = nome;
        this.preco = preco;
    }

    getNome(){
        return this.nome;
    }

    getPreco(){
        return this.preco;
    }

    setMarca(nome){
        this.marca = nome;
    }

    setPreco(preco){
        this.preco = preco;
    }

    toString(){
        return `Nome: ${this.nome}\nPreço: ${this.preco}`;
    }
}

export default Tinta;