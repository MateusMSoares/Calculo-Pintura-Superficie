import React, { useState, useEffect } from 'react';

const CampoRetangulo = ({ calcularArea }) => {
    const [largura, setLargura] = useState(0);
    const [altura, setAltura] = useState(0);

    useEffect(() => {
        const area = largura * altura;
        calcularArea(area);
    }, [largura, altura, calcularArea]);

    return (
        <div>
            <label>Largura:</label>
            <input
                type="number"
                value={largura}
                onChange={(e) => setLargura(parseFloat(e.target.value))}
            />
            <label>Altura:</label>
            <input
                type="number"
                value={altura}
                onChange={(e) => setAltura(parseFloat(e.target.value))}
            />
        </div>
    );
};

export default CampoRetangulo;
