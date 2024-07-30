// data/calculatePaintCost.js

export const calculatePaintCost = (surfaceType, dimensions, paintTypes, paintType, multiplicidade) => {
    const dim1 = parseFloat(dimensions.dimensao1);
    const dim2 = parseFloat(dimensions.dimensao2);
    const selectedPaint = paintTypes[paintType];

    if (!selectedPaint) {
        console.error("Tinta selecionada não encontrada.");
        return { result: 'Tipo de tinta inválido', area: null };
    }

    const paintValue = selectedPaint.value;

    if (isNaN(dim1) || (surfaceType === 'cone' && isNaN(dim2))) {
        return { result: 'Dimensões inválidas', area: null };
    }

    let calculatedArea = 0;
    switch (surfaceType) {
        case 'cone':
            calculatedArea = Math.PI * dim1 * dim2;
            break;
        case 'circulo':
            calculatedArea = Math.PI * Math.pow(dim1, 2);
            break;
        case 'quadrado':
            calculatedArea = Math.pow(dim1, 2);
            break;
        case 'retangulo':
            calculatedArea = dim1 * dim2;
                break;
        default:
            return { result: 'Tipo de superfície inválido', area: null };
    }

    const totalCost = (calculatedArea * paintValue) * multiplicidade;
    return { result: totalCost.toFixed(2), area: calculatedArea.toFixed(2) };
};

export default calculatePaintCost;