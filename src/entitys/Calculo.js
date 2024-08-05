class Calculo {
    constructor(unidade, equipamento) {
        this.unidade = unidade;
        this.equipamento = equipamento;
        this.detalhes = this.calcularDetalhes();
    }

    calcularDetalhes() {
        return this.equipamento.getAtributos().map(atributo => {
            const { area } = atributo;
            const tinta = atributo.getTinta();
            const custo = area * tinta.getPreco();
            return {
                atributo: atributo.getNome(),
                forma: atributo.getForma(),
                area,
                tinta: tinta.getNome(),
                precoTinta: tinta.getPreco(),
                custo
            };
        });
    }

    getDetalhes() {
        return this.detalhes;
    }

    toString() {
        return `Unidade: ${this.unidade.getNome()} - ${this.unidade.getTag()}
                Equipamento: TAG: ${this.equipamento.getTag()} - CLASSE: ${this.equipamento.getClasse()}
                Detalhes:${this.detalhes.map(d => `Atributo: ${d.atributo}, Forma: ${d.forma}, Área: ${d.area}m^2, Tinta: ${d.tinta}, 
                        Preço da Tinta: R$${d.precoTinta}, Custo: R$${d.custo}`).join('\n')}`;
    }
}

export default Calculo;