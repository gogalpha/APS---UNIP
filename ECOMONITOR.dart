import 'package:flutter/material.dart';

void main() {
  runApp(EcoMonitorApp());
}

class EcoMonitorApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'EcoMonitor',
      theme: ThemeData(primarySwatch: Colors.green),
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  HomeScreen({Key? key}) : super(key: key);

  final List<String> categories = [
    'Poluição',
    'Trânsito',
    'Alagamentos',
    'Desmatamento',
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('EcoMonitor')),
      body: ListView.builder(
        itemCount: categories.length,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text(categories[index]),
            trailing: Icon(Icons.arrow_forward),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (_) => DetailScreen(category: categories[index]),
                ),
              );
            },
          );
        },
      ),
    );
  }
}

class DetailScreen extends StatelessWidget {
  final String category;

  const DetailScreen({Key? key, required this.category}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final Map<String, String> mockData = {
      'Poluição': 'Qualidade do ar: Moderada\nPM2.5: 35 µg/m³\nPM10: 50 µg/m³',
      'Trânsito': 'Trânsito intenso na Av. Paulista\nVelocidade média: 12 km/h',
      'Alagamentos': 'Alagamento registrado na Rua das Flores\nNível da água: 30 cm',
      'Desmatamento': 'Área desmatada: 2 hectares\nRegião: Zona Sul',
    };

    return Scaffold(
      appBar: AppBar(title: Text('Detalhes - $category')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Text(
          mockData[category] ?? 'Dados não disponíveis.',
          style: TextStyle(fontSize: 18),
        ),
      ),
    );
  }
}