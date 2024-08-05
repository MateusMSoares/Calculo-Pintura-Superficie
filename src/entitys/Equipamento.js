class Equipamento {
    constructor(tag, classe, tagUnidade, atributos = []) {
        this.tag = tag;
        this.classe = classe;
        this.tagUnidade = tagUnidade;
        this.atributos = atributos; // lista de objetos Atributo, pode ser vazio ou ter n atributos
    }

    getTag() {
        return this.tag;
    }

    getClasse() {
        return this.classe;
    }

    getAtributos() {
        return this.atributos;
    }

    setTag(tag) {
        this.tag = tag;
    }

    setClasse(classe) {
        this.classe = classe;
    }

    setAtributos(atributos) {
        this.atributos = atributos;
    }

    addAtributo(atributo) {
        this.atributos.push(atributo);
    }

    removeAtributo(nome) {
        this.atributos = this.atributos.filter(a => a.nome !== nome);
    }

    toString() {
        return `tag: ${this.tag} classe: ${this.classe}\nAtributos: \n${this.atributos.map(a => a.toString()).join('\n')}`;
    }
}

export default Equipamento;
