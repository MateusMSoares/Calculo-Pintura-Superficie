class Unidade{
    constructor(nome, tag){
        this.nome = nome;
        this.tag = tag;
    }

    getNome(){
        return this.nome;
    }

    getTag(){
        return this.tag;
    }

    getEquipamentos(){
        return this._equipamentos;
    }

    setNome(nome){
        this.nome = nome;
    }

    setTag(tag){    
        this.tag = tag;
    }

    setEquipamentos(equipamentos){
        this._equipamentos = equipamentos;
    }

    addEquipamento(equipamento){
        this._equipamentos.push(equipamento);
    }

    removeEquipamento(tag){
        this._equipamentos = this._equipamentos.filter(e => e.tag !== tag);
    }

    toString() {
        return `Nome: ${this.nome}\nTag: ${this.tag}\nEquipamentos: ${this._equipamentos.map(e => e.toString()).join('\n\n')}`;
    }
}

export default Unidade;