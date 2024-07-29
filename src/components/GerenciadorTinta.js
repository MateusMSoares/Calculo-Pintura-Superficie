import React, { useState, useEffect } from 'react';
import { addPaint, getPaints, updatePaint, deletePaint } from '../data/DataBase';
import '../styles/GerenciadorTinta.css';

const PaintManager = ({ closeModal }) => {
    const [paintTypes, setPaintTypes] = useState({});
    const [newPaintName, setNewPaintName] = useState('');
    const [newPaintValue, setNewPaintValue] = useState('');
    const [editing, setEditing] = useState(null);

    useEffect(() => {
        // Carregar as tintas quando o componente for montado
        const fetchPaints = async () => {
            const paints = await getPaints();
            setPaintTypes(paints);
        };

        fetchPaints();
    }, []);

    const handleAddPaint = async () => {
        if (newPaintName && newPaintValue) {
            try {
                await addPaint(newPaintName, parseFloat(newPaintValue));
                // Atualiza a lista de tintas após adicionar
                const paints = await getPaints();
                setPaintTypes(paints);
                setNewPaintName('');
                setNewPaintValue('');
            } catch (error) {
                console.error('Erro ao adicionar tinta: ', error);
            }
        }
    };

    const handleEditPaint = (id) => {
        const paint = paintTypes[id];
        setEditing({ id, name: paint.name, value: paint.value });
    };

    const handleSaveEdit = async () => {
        if (editing && editing.name && editing.value) {
            try {
                await updatePaint(editing.id, editing.name, parseFloat(editing.value));
                // Atualiza a lista de tintas após atualizar
                const paints = await getPaints();
                setPaintTypes(paints);
                setEditing(null);
            } catch (error) {
                console.error('Erro ao atualizar tinta: ', error);
            }
        }
    };

    const handleDeletePaint = async (id) => {
        try {
            await deletePaint(id);
            // Atualiza a lista de tintas após excluir
            const paints = await getPaints();
            setPaintTypes(paints);
        } catch (error) {
            console.error('Erro ao excluir tinta: ', error);
        }
    };

    return (
        <div className="paint-manager">
            <h2>Gerenciar Tintas</h2>
            <div className="form-group">
                <input 
                    type="text" 
                    placeholder="Nome da Tinta" 
                    value={newPaintName} 
                    onChange={(e) => setNewPaintName(e.target.value)} 
                />
                <input 
                    type="number" 
                    placeholder="Valor da Tinta" 
                    value={newPaintValue} 
                    onChange={(e) => setNewPaintValue(e.target.value)} 
                />
                <button onClick={handleAddPaint}>Adicionar Tinta</button>
            </div>
            {Object.keys(paintTypes).map((id) => {
                const paint = paintTypes[id];
                return (
                    <div key={id} className="paint-item">
                        <span>{paint.name} - R${paint.value}</span>
                        <button onClick={() => handleEditPaint(id)}>Editar</button>
                        <button onClick={() => handleDeletePaint(id)}>Excluir</button>
                    </div>
                );
            })}
            {editing && (
                <div className="edit-form">
                    <input 
                        type="text" 
                        value={editing.name} 
                        onChange={(e) => setEditing({ ...editing, name: e.target.value })} 
                    />
                    <input 
                        type="number" 
                        value={editing.value} 
                        onChange={(e) => setEditing({ ...editing, value: e.target.value })} 
                    />
                    <button onClick={handleSaveEdit}>Salvar</button>
                </div>
            )}
            <button onClick={closeModal}>Fechar</button>
        </div>
    );
};

export default PaintManager;
