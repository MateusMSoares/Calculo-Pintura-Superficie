// PaginaConfiguracao.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import GerenciadorTinta from './GerenciadorTinta';
import GerenciarEquipamento from './GerenciarEquipamento';
import '../styles/PaginaConfiguracao.css';

const PaginaConfiguracao = () => {
  const navigate = useNavigate();
  const [activeComponent, setActiveComponent] = useState(null);
  const [showGerenciamento, setShowGerenciamento] = useState(false);
  const [showCalculos, setShowCalculos] = useState(false);

  const renderComponent = () => {
    switch (activeComponent) {
      case 'GerenciarUnidade':
        return <p>Gerenciar Unidade</p>; // Substitua pelo componente real
      case 'GerenciarEquipamentos':
        return <GerenciarEquipamento />;
      case 'GerenciarAcessorios':
        return <p>Gerenciar Acessórios</p>; // Substitua pelo componente real
      case 'ClassesEquipamentos':
        return <p>Classes de Equipamentos</p>; // Substitua pelo componente real
      case 'GerenciadorTinta':
        return <GerenciadorTinta />;
      default:
        return <p>Selecione uma opção no menu à esquerda.</p>;
    }
  };
 

  return (
    <div className="config-container">
      <div className="titulo">
        <h1>Configuração</h1>
      </div>
      <div className="content">
        <div className="opcoes">
            <p className="titulo-opcoes" onClick={() => setShowGerenciamento(!showGerenciamento)}>
                Gerenciamento {showGerenciamento ? '▲' : '▼'}
            </p>
            {showGerenciamento && (
                <div className="submenu">
                    <button onClick={() => setActiveComponent('GerenciarUnidade')}>Gerenciar Unidade</button>
                    <button onClick={() => setActiveComponent('GerenciarEquipamentos')}>Gerenciar Equipamentos</button>
                    <button onClick={() => setActiveComponent('GerenciarAcessorios')}>Gerenciar Acessórios</button>
                    <button onClick={() => setActiveComponent('ClassesEquipamentos')}>Classes de Equipamentos</button>
                    <button onClick={() => setActiveComponent('GerenciadorTinta')}>Gerenciar Tintas</button>
                </div>
            )}
            <p className="titulo-opcoes" onClick={() => setShowCalculos(!showCalculos)}>
                Calculos {showCalculos ? '▲' : '▼'}
            </p>
            {showCalculos && (
                <div className="submenu">
                    <button onClick={() => setActiveComponent('Calculo1')}>Cálculo 1</button>
                    <button onClick={() => setActiveComponent('Calculo2')}>Cálculo 2</button>
                    {/* Adicione mais itens conforme necessário */}
                </div>
            )}
            <button className="btn-voltar" onClick={() => navigate('/')}>Voltar</button>
        </div>
        <div className="interacao">
          {renderComponent()}
        </div>
      </div>
    </div>
  );
};

export default PaginaConfiguracao;
