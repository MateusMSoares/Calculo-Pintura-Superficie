import firebase_admin
from firebase_admin import credentials, firestore

# Caminho para o arquivo da chave JSON que você obteve do Firebase
cred = credentials.Certificate('caminho/para/sua/chave/firebase-admin-sdk.json')

# Inicializando o SDK com a chave
firebase_admin.initialize_app(cred)

# Inicializando o cliente Firestore
db = firestore.client()

# Exemplo: Adicionando um documento no Firestore
doc_ref = db.collection('equipamentos').document('tanque001')
doc_ref.set({
    'nome': 'Tanque 001',
    'tipo': 'Tanque',
    'diametro': 10.0,
    'altura': 5.0
})

print("Documento adicionado com sucesso!")
