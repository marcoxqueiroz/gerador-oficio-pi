(function(){
  const YEAR_MIN = 1900;
  const YEAR_MAX = 3000;

  function pad4(n){ return String(n).padStart(4, "0"); }
  function formatOficio(seq, year){ return `${pad4(seq)}/${year}`; }

  function keyFor(year){ return `oficio_last_${year}`; }

  function getLast(year){
    const v = localStorage.getItem(keyFor(year));
    return v ? parseInt(v, 10) : 0;
  }

  function setLast(year, seq){
    localStorage.setItem(keyFor(year), String(seq));
  }

  function validateYear(year){
    if (!Number.isInteger(year)) return "Ano deve ser um número inteiro.";
    if (year < YEAR_MIN || year > YEAR_MAX) return `Ano inválido. Use entre ${YEAR_MIN} e ${YEAR_MAX}.`;
    return null;
  }

  // ===== GERAR (gerar.html) =====
  const yearInput = document.querySelector("#yearInput");
  const btnConfirm = document.querySelector("#btnConfirm");
  const alertBox = document.querySelector("#alertBox");

  const modal = document.querySelector("#modal");
  const modalOficio = document.querySelector("#modalOficio");
  const modalClose = document.querySelector("#modalClose");

  if (yearInput) {
    yearInput.value = new Date().getFullYear();
  }

  function openModal(oficioText){
    if (!modal) return;
    modalOficio.textContent = `Ofício Nº ${oficioText}`;
    modal.style.display = "flex";
  }

  function closeModal(){
    if (!modal) return;
    modal.style.display = "none";
  }

  if (modalClose) modalClose.addEventListener("click", closeModal);
  if (modal) {
    modal.addEventListener("click", (e) => {
      if (e.target === modal) closeModal();
    });
  }

  if (btnConfirm) {
    btnConfirm.addEventListener("click", () => {
      alertBox.style.display = "none";

      const year = parseInt(yearInput.value, 10);
      const err = validateYear(year);
      if (err){
        alertBox.textContent = err;
        alertBox.style.display = "block";
        return;
      }

      const next = getLast(year) + 1;
      setLast(year, next);

      const oficio = formatOficio(next, year);
      openModal(oficio);
    });
  }

  // ===== HISTÓRICO (historico.html) =====
  const tbody = document.querySelector("#historyBody");
  if (tbody){
    const rows = [];

    for (let i=0; i<localStorage.length; i++){
      const k = localStorage.key(i);
      if (k && k.startsWith("oficio_last_")){
        const year = parseInt(k.replace("oficio_last_", ""), 10);
        const seq = parseInt(localStorage.getItem(k), 10);
        if (Number.isInteger(year) && Number.isInteger(seq)){
          rows.push({year, seq});
        }
      }
    }

    rows.sort((a,b)=>a.year-b.year);

    tbody.innerHTML = "";
    if (rows.length === 0){
      const tr = document.createElement("tr");
      tr.innerHTML = `<td colspan="2">Sem registros ainda.</td>`;
      tbody.appendChild(tr);
    } else {
      rows.forEach(r=>{
        const tr = document.createElement("tr");
        tr.innerHTML = `<td>${pad4(r.seq)}</td><td>${r.year}</td>`;
        tbody.appendChild(tr);
      });
    }
  }
})();
