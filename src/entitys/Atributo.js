class Atributo{
    constructor(nome, forma, area, tinta){
        this.nome = nome;
        this.forma = forma;
        this.area = area;
        this.tinta = tinta;
    }

    getNome(){
        return this.nome;
    }

    getForma(){
        return this.forma;
    }

    getArea(){
        return this.area;
    }

    getTinta(){
        return this.tinta;
    }

    setNome(nome){
        this.nome = nome;
    }

    setForma(forma){
        this.forma = forma;
    }

    setArea(area){  
        this.area = area;
    }

    setTinta(tinta){
        this.tinta = tinta;
    }  

    toString() {
        return `nome: ${this.nome}, forma: ${this.forma}, area: ${area}, tinta: ${tinta}`;
    }
}

export default Atributo;