import React, { useState } from 'react';
import Equipamento from '../entitys/Equipamento';
import SeletorSuperfice from './SeletorSuperfice';
import '../styles/CriarEquipamento.css';

const GerenciarEquipamento = () => {
    const [tag, setTag] = useState("");
    const [classe, setClasse] = useState("");
    const [tagUnidade, setTagUnidade] = useState("");
    const [atributos, setAtributos] = useState([]);
    const [surfaceType, setSurfaceType] = useState("");
    const [multiplicidade, setMultiplicidade] = useState(1);
    const classes = ["A", "B"];
    const unidades = ["Unidade1", "Unidade2"]; // Exemplo de unidades

    const handleAddAtributo = () => {
        setAtributos([...atributos, { nome: "", valor: "" }]);
    };

    const handleAtributoChange = (index, field, value) => {
        const newAtributos = [...atributos];
        newAtributos[index][field] = value;
        setAtributos(newAtributos);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const equipamento = new Equipamento(tag, classe, tagUnidade, atributos);
        console.log(equipamento);
        // Aqui você pode enviar o equipamento para o backend ou realizar outras ações
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Tag:</label>
                <input type="text" value={tag} onChange={(e) => setTag(e.target.value)} />
            </div>
            <div>
                <label>Classe:</label>
                <select value={classe} onChange={(e) => setClasse(e.target.value)}>
                    {classes.map((classe) => (
                        <option key={classe} value={classe}>
                            {classe}
                        </option>
                    ))}
                </select>
            </div>
            <div>
                <label>Tag Unidade:</label>
                <select value={tagUnidade} onChange={(e) => setTagUnidade(e.target.value)}>
                    <option value="">Selecione uma unidade</option>
                    {unidades.map((tagUnidade) => (
                        <option key={tagUnidade} value={tagUnidade}>
                            {tagUnidade}
                        </option>
                    ))}
                </select>
            </div>
            <div>
                <label>Atributos:</label>
                <br />
                <SeletorSuperfice
                    surfaceType={surfaceType}
                    setSurfaceType={setSurfaceType}
                    multiplicidade={multiplicidade}
                    setMultiplicidade={setMultiplicidade}
                />
                <button type="button" onClick={handleAddAtributo}>
                    Adicionar Atributo
                </button>
            </div>
            <button type="submit">Salvar Equipamento</button>
        </form>
    );
};

export default GerenciarEquipamento;
