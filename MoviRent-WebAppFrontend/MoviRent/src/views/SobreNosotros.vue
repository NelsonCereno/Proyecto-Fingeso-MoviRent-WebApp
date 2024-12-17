<template>
  <div class="SobreNosotros">

    <h1>Quienes Somos</h1>
    <p>
      En MoviRent, somos una empresa dedicada a ofrecer las mejores opciones de alquiler de vehículos para satisfacer tus 
      necesidades de transporte. Nuestro compromiso es brindarte un servicio de calidad, con una amplia variedad de vehículos y precios competitivos.
    </p>

    <h2>Nuestra Misión</h2>
    <p>
      Nuestra misión es proporcionar soluciones de movilidad accesibles y confiables, asegurando la satisfacción de nuestros clientes a través de un servicio excepcional y una flota de vehículos bien mantenidos.
    </p>

    <h2>Nuestros Valores</h2>
    <ul>
      <li>Compromiso con la calidad</li>
      <li>Atención al cliente</li>
      <li>Innovación constante</li>
      <li>Responsabilidad y transparencia</li>
    </ul>

    <h2>Contáctanos</h2>
    <p>
      Si tienes alguna pregunta o necesitas más información, no dudes en contactarnos:
    </p>
    <ul>
      <li>Teléfono: +569 50225312</li>
      <li>Email: contacto@movirent.com</li>
      <li>Dirección: TutoLaWawa 1113, Santiago, Chile</li>
    </ul>
  </div>
</template>

<style scoped>
.SobreNosotros {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background: #000000;
  border: 1px solid #f6f8f7;
  border-radius: 5px;
  color: #000000;
  text-align: left;
  overflow-y: auto;
  flex-wrap: wrap;
  background-color: #f6f8f7;

  /*ajustar para el vice*/
  max-height: 700px;
}

h1, h2 {
  text-align: left;
  color: #000000;
  margin-bottom: 20px;
  font-size: 2em;
  font-weight: bold;
  
}

p, ul {
  font-size: 1.2em;
  line-height: 1.6;
}

ul {
  list-style-type: disc;
  padding-left: 20px;
}

li {
  margin-bottom: 10px;
}
</style>