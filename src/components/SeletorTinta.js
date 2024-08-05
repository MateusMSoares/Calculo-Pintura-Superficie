import React, { useState, useEffect } from 'react';
import { getPaints } from '../data/DataBase';

const PaintTypeSelector = ({ paintType, setPaintType, openPaintManager, setPaintTypes }) => {
    const [paintTypes, setLocalPaintTypes] = useState({});

    useEffect(() => {
        const fetchPaints = async () => {
            try {
                const paints = await getPaints();
                console.log("Dados das tintas recebidos:", paints);
                setLocalPaintTypes(paints);
                setPaintTypes(paints); // Atualize o estado no PaintCalculator
            } catch (error) {
                console.error("Erro ao buscar tintas:", error);
            }
        };

        fetchPaints();
    }, [setPaintTypes]);

    return (
        <div className="form-group paint-selector">
            <label>Tipo de tinta:</label>
            <div className="paint-selector-container">
                <select 
                        value={paintType} 
                        onChange={(e) => {
                            const selectedId = e.target.value;
                            console.log("Tipo de tinta selecionado ID:" + selectedId + "valor " + paintTypes[selectedId]?.value);
                            setPaintType(selectedId);
                        }}
                    >
                    <option value="">Selecione</option>
                    {Object.keys(paintTypes).map((id) => {
                        const paint = paintTypes[id];
                        return (
                            <option key={id} value={id}>{paint.name} - R${paint.value}</option>
                        );
                    })}
                </select>
                <button className="add-paint-button" onClick={openPaintManager} style={{ marginLeft: '10px', marginTop: '20px' }}>
                  <i className="fa fa-cog" aria-hidden="true"></i> trocar pagina
                </button>
            </div>
        </div>
    );
};

export default PaintTypeSelector;
