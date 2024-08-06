import React, { useState } from 'react';
import CriarEquipamento from './equipamentos/CriarEquipamento';

const Tab = ({ label, onClick, active }) => (
  <button 
    onClick={onClick}
  >
    {label}
  </button>
);

const Tabs = () => {
  const [activeTab, setActiveTab] = useState(0);
  const tabs = [
    { label: 'Criar Equipamento', content: <CriarEquipamento /> },
    { label: 'Tab 2', content: 'Conteúdo da Tab 2' },
    { label: 'Tab 3', content: 'Conteúdo da Tab 3' }
  ];

  return (
    <div>
      <div style={{ display: 'flex', borderBottom: '1px solid #ddd' }}>
        {tabs.map((tab, index) => (
          <Tab 
            key={index} 
            label={tab.label} 
            onClick={() => setActiveTab(index)} 
            active={activeTab === index} 
          />
        ))}
      </div>
      <div style={{ padding: '0px', paddingTop: '10px' }}>
        {tabs[activeTab].content}
      </div>
    </div>
  );
};

export default Tabs;
