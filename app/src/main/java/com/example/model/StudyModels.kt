package com.example.model

data class StudyNoteSection(
    val heading: String,
    val points: List<String>
)

data class StudyMaterial(
    val id: Int,
    val subject: String,
    val title: String,
    val desc: String,
    val isPaid: Boolean,
    val price: Int,
    val link: String,
    val badgeTag: String = "2025-26 Syllabus",
    val sections: List<StudyNoteSection> = emptyList()
)

data class GuideCardItem(
    val title: String,
    val subtitle: String,
    val badge: String
)

object StudyHubDefaults {
    const val DEFAULT_WHATSAPP = "919999999999"
    const val DEFAULT_UPI = "aapka-upi@okaxis"

    val SUBJECTS = listOf(
        "All",
        "Hindi",
        "English",
        "Physics",
        "Chemistry",
        "Maths",
        "Biology",
        "Arts/Commerce"
    )

    val HOW_TO_EARN = listOf(
        GuideCardItem(
            title = "Freemium Model (Best Option)",
            subtitle = "Free me official syllabus, formula sheets aur 1-2 sample chapters dein taaki students aap par bharosa karein. Apne best handwritten notes ya solved question banks ko bahut kam daam (jaise 29 INR ya 49 INR) me rakhein.",
            badge = "Most Popular"
        ),
        GuideCardItem(
            title = "Google AdSense / Ads",
            subtitle = "Jab aapke app/website par rozana achha traffic aane lage (khaaskar Dec se March exam season me), tab aap Google AdSense lagakar free PDFs se bhi paise kama sakte hain.",
            badge = "Passive Income"
        ),
        GuideCardItem(
            title = "YouTube / Telegram Promotion",
            subtitle = "Ek free Telegram channel aur YouTube Shorts banayein jahan aap 12th ke study tips dein aur apne is app ka link share karein.",
            badge = "Free Traffic"
        ),
        GuideCardItem(
            title = "Book Affiliate Links",
            subtitle = "Amazon ya Flipkart ke affiliate account se 12th UP Board ki best books/sample papers ke links add karke commission kama sakte hain.",
            badge = "Extra Bonus"
        )
    )

    val LEGAL_AND_SAFETY_RULES = listOf(
        GuideCardItem(
            title = "Copyright Warning",
            subtitle = "Kisi dusre publisher ki kitaab, question bank (jaise Vidya, Mastermind, Avatar) ko scan karke bilkul share ya sell na karein. Yeh illegal hai. Hamesha apne khud ke handwritten notes, khud ke banaye important questions, ya UPMSP ke official free model papers hi share karein.",
            badge = "Strict Rule"
        ),
        GuideCardItem(
            title = "Payment Account",
            subtitle = "Agar aap 18 saal se kam umar ke hain, toh app me apni UPI ID ki jagah apne parents ki UPI ID aur permission ka istemal karein.",
            badge = "Safety First"
        ),
        GuideCardItem(
            title = "Trust Banayein",
            subtitle = "Jo bhi student payment kare, use turant aur imandari se PDF bhejein, tabhi aapke notes ki demand badhegi.",
            badge = "Student Trust"
        )
    )

    val HOW_TO_LAUNCH_STEPS = listOf(
        GuideCardItem(
            title = "Step 1 (File Save Karein)",
            subtitle = "Niche diye gaye 'complete_app_code_html' ko copy karein aur apne phone ya laptop me ek file banakar 'index.html' naam se save karein.",
            badge = "Step 1"
        ),
        GuideCardItem(
            title = "Step 2 (PDF Links Lagayein)",
            subtitle = "Apne PDFs ko Google Drive par upload karein, 'Share -> Anyone with the link' on karein, aur code me 'pdfData' ke andar 'https://drive.google.com/' ki jagah apna link paste karein. Sath hi apna WhatsApp number aur UPI ID update karein.",
            badge = "Step 2"
        ),
        GuideCardItem(
            title = "Step 3 (Free Hosting)",
            subtitle = "'app.netlify.com/drop' website par jayein aur apni 'index.html' file wahan drag-and-drop kar dein. 10 second me aapko ek free live website link mil jayega.",
            badge = "Step 3"
        ),
        GuideCardItem(
            title = "Step 4 (App Jaisa Banayein)",
            subtitle = "Chrome browser me apna link kholein aur 'Add to Home Screen' par click karein, yeh phone me ek asli app ki tarah install ho jayega. Baad me aap chahein toh WebView se iski Android APK bhi bana sakte hain.",
            badge = "Step 4"
        )
    )

    val INITIAL_MATERIALS = listOf(
        StudyMaterial(
            id = 1,
            subject = "Hindi",
            title = "12th Hindi Sahitya & Jivan Parichay (Free)",
            desc = "UP Board latest syllabus ke hisab se important jivan parichay aur vyakhya.",
            isPaid = false,
            price = 0,
            link = "https://drive.google.com/",
            badgeTag = "100 Marks Prep",
            sections = listOf(
                StudyNoteSection(
                    heading = "Most Important Gadya Lekhak Jivan Parichay (5 Marks)",
                    points = listOf(
                        "Vasudev Sharan Agrawal: Janm 1904 (Meerut). Pramukh Kritiyan: Prithviputra, Kalpalata, Bharat ki Ekta. Path: 'Rashtra ka Swaroop'.",
                        "Acharya Hazari Prasad Dwivedi: Janm 1907 (Ballia). Kritiyan: Ashok ke Phool, Kutaj, Banbhatt ki Atmakatha.",
                        "Pro. G. Sundar Reddy: 'Bhasha aur Adhunikta' — Bhasha ki gatishilta aur navin shabdon ka mahatva."
                    )
                ),
                StudyNoteSection(
                    heading = "Most Important Kavi Jivan Parichay (5 Marks)",
                    points = listOf(
                        "Jaishankar Prasad: Chhayavad ke pravartak. Mahakavya: Kamayani (Shraddha Manu).",
                        "Mahadevi Verma: Adhunik Meera. Kritiyan: Yama, Nihar, Rashmi, Nirja.",
                        "Ramdhari Singh 'Dinkar': Rashtrakavi. Kritiyan: Urvashi, Rashmirathi, Kurukshetra (Abhinav Manushya)."
                    )
                ),
                StudyNoteSection(
                    heading = "Kahani, Khandkavya & Sanskrit Vyakhya Tips",
                    points = listOf(
                        "Panchlight (Phanishwar Nath Renu) aur Bahadur (Amarkant) ka saransh har saal pucha jata hai.",
                        "Sanskrit Digdarshika: 'Subhashit Ratnani' ke sabhi shlok ka anuvad zaroor taiyar karein (7 Marks pakke)."
                    )
                )
            )
        ),
        StudyMaterial(
            id = 2,
            subject = "English",
            title = "12th English Prose, Poetry & Grammar Notes",
            desc = "Important central ideas, unseen passages, letter aur article writing PDF.",
            isPaid = false,
            price = 0,
            link = "https://drive.google.com/",
            badgeTag = "Full Grammar + Prose",
            sections = listOf(
                StudyNoteSection(
                    heading = "Top 3 Central Ideas in Poetry (4 Marks Guaranteed)",
                    points = listOf(
                        "1. My Mother at Sixty-Six (Kamala Das): Theme of aging, fear of separation, and filial love.",
                        "2. Keeping Quiet (Pablo Neruda): Importance of quiet introspection, universal brotherhood, and peace.",
                        "3. A Thing of Beauty (John Keats): Nature's beauty is an eternal source of joy and removes gloom."
                    )
                ),
                StudyNoteSection(
                    heading = "Letter & Article Writing Format (20 Marks)",
                    points = listOf(
                        "Letter to the Editor: Frequent breakdown of electricity / water supply & cleanliness drive in your city.",
                        "Important Articles: Women Safety & Empowerment, Digital India, Pollution Control, Importance of Games & Sports."
                    )
                ),
                StudyNoteSection(
                    heading = "Grammar Quick Rules (Direct/Indirect, Synthesis, Transformation)",
                    points = listOf(
                        "Direct to Indirect: Universal truths do not change tense. Optative sentences use wished/prayed.",
                        "Synthesis: Use Participle when one action immediately follows another by the same subject."
                    )
                )
            )
        ),
        StudyMaterial(
            id = 3,
            subject = "Physics",
            title = "Physics Complete Handwritten Notes (Part 1 & 2)",
            desc = "Sabhi chapters ke derivations, formulas aur board exam important numericals.",
            isPaid = true,
            price = 49,
            link = "https://drive.google.com/",
            badgeTag = "Topper Handwritten",
            sections = listOf(
                StudyNoteSection(
                    heading = "Part 1: Electrostatics & Current Electricity Derivations",
                    points = listOf(
                        "Gauss's Theorem: Φ = q / ε₀ aur Anant lambai ke aaveshit taar ke nikath vaidyut kshetra E = λ / (2πε₀r).",
                        "Parallel Plate Capacitor with Dielectric Slab: C = ε₀A / (d - t + t/K).",
                        "Kirchhoff's Laws (KCL & KVL) aur Wheatstone Bridge संतुलन शर्त: P/Q = R/S."
                    )
                ),
                StudyNoteSection(
                    heading = "Part 2: Ray Optics, Wave Optics & Modern Physics",
                    points = listOf(
                        "Lens Maker's Formula: 1/f = (n - 1)(1/R₁ - 1/R₂).",
                        "Huygens' Principle: Tarangagra ke adhar par paravartan aur apvartan ki vyakhya (5 Marks Fix).",
                        "Einstein's Photoelectric Equation: E_k = h(ν - ν₀) aur p-n Junction Diode Rectifier."
                    )
                )
            )
        ),
        StudyMaterial(
            id = 4,
            subject = "Chemistry",
            title = "Chemistry Name Reactions & Numericals PDF",
            desc = "Organic aur Inorganic chemistry ki sabhi important reactions ek hi PDF me.",
            isPaid = true,
            price = 39,
            link = "https://drive.google.com/",
            badgeTag = "35+ Name Reactions",
            sections = listOf(
                StudyNoteSection(
                    heading = "Top Organic Name Reactions (Har Saal Aane Wali)",
                    points = listOf(
                        "Reimer-Tiemann Reaction: Phenol + CHCl₃ + NaOH -> Salicylaldehyde.",
                        "Cannizzaro Reaction: Aldehydes without alpha-H (HCHO, C₆H₅CHO) + conc. NaOH -> Alcohol + Salt of Acid.",
                        "Aldol Condensation, Wurtz-Fittig, Hoffmann Bromamide, Sandmeyer & Rosenmund Reduction."
                    )
                ),
                StudyNoteSection(
                    heading = "Physical Chemistry Must-Do Numericals (Solutions, Electrochemistry, Kinetics)",
                    points = listOf(
                        "Raoult's Law & Colligative Properties: ΔTb = Kb × m, Osmotic Pressure π = CRT.",
                        "Nernst Equation: E = E° - (0.0591 / n) log Q at 298 K.",
                        "First Order Reaction: k = (2.303 / t) log(a / (a - x)) aur Half-life t₁/₂ = 0.693 / k."
                    )
                )
            )
        ),
        StudyMaterial(
            id = 5,
            subject = "Maths",
            title = "12th Maths Formula Sheet & Model Paper",
            desc = "Integration, Differentiation, Matrices ke sabhi formulas aur solved paper.",
            isPaid = false,
            price = 0,
            link = "https://drive.google.com/",
            badgeTag = "All Formulas PDF",
            sections = listOf(
                StudyNoteSection(
                    heading = "Calculus Master Formulas (44 Marks Weightage)",
                    points = listOf(
                        "Integration by Parts: ∫ u·v dx = u∫v dx - ∫(du/dx · ∫v dx) dx (ILATE Rule).",
                        "Definite Integral Property: ∫₀ᵃ f(x) dx = ∫₀ᵃ f(a - x) dx (King's Property for 8-Mark Questions).",
                        "Shortest Distance between Skew Lines: d = |(b₁ × b₂) · (a₂ - a₁)| / |b₁ × b₂|."
                    )
                ),
                StudyNoteSection(
                    heading = "Matrices & Determinants 8-Mark Problem Method",
                    points = listOf(
                        "System of Linear Equations by Matrix Method: AX = B => X = A⁻¹B where A⁻¹ = adj(A) / |A|.",
                        "Maxima & Minima word problems: Cone inscribed in a sphere / cylinder of maximum volume."
                    )
                )
            )
        ),
        StudyMaterial(
            id = 6,
            subject = "Biology",
            title = "Biology Diagrams & Important Questions Bank",
            desc = "Board exams me baar-baar aane wale diagrams aur 5-mark questions.",
            isPaid = true,
            price = 29,
            link = "https://drive.google.com/",
            badgeTag = "HD Labeled Diagrams",
            sections = listOf(
                StudyNoteSection(
                    heading = "Must-Practice Labeled Diagrams (5 Marks)",
                    points = listOf(
                        "Pushp me Nishachan (Double Fertilization in Angiosperms) & Ovule (Bijand) ki sanrachna.",
                        "Human Male & Female Reproductive System + Spermatogenesis vs Oogenesis.",
                        "Watson & Crick DNA Double Helix Model aur Antibody (IgG) Molecule Structure."
                    )
                ),
                StudyNoteSection(
                    heading = "Genetics & Biotechnology High-Scoring Topics",
                    points = listOf(
                        "Mendel ke Niyam, Incomplete Dominance, Sex-linked Inheritance (Haemophilia & Colour Blindness).",
                        "PCR (Polymerase Chain Reaction), Recombinant DNA Technology, Bt-Cotton & Gene Therapy."
                    )
                )
            )
        ),
        StudyMaterial(
            id = 7,
            subject = "Arts/Commerce",
            title = "History, Geography & Economics Important PDF",
            desc = "Arts aur Commerce stream ke sabhi important long aur short questions.",
            isPaid = false,
            price = 0,
            link = "https://drive.google.com/",
            badgeTag = "Arts + Commerce",
            sections = listOf(
                StudyNoteSection(
                    heading = "Economics (Vyashti & Samashti Arthshastra) Top Questions",
                    points = listOf(
                        "Mang ki Kimat Loch (Price Elasticity of Demand) aur Upbhokta Santulan.",
                        "Rashtriya Aay (National Income) ki ganana ki vidhiyan aur RBI ke karya (Mudra aur Banking)."
                    )
                ),
                StudyNoteSection(
                    heading = "History, Civics & Geography Map & Long Answers",
                    points = listOf(
                        "Harappa Sabhyata ki Nagar Yojna, 1857 ki Kranti, aur Mahatma Gandhi ke Rashtriya Andolan.",
                        "Bharatiya Samvidhan ki Pramukh Visheshtayein, Manav Bhugol aur Bharat ke Pramukh Udyog."
                    )
                )
            )
        )
    )

    fun generateCompleteHtmlCode(
        whatsappNumber: String,
        upiId: String,
        materials: List<StudyMaterial>
    ): String {
        val pdfArrayJs = materials.joinToString(",\n      ") { item ->
            val escapedTitle = item.title.replace("'", "\\'")
            val escapedDesc = item.desc.replace("'", "\\'")
            val escapedSubject = item.subject.replace("'", "\\'")
            val typeStr = if (item.isPaid) "paid" else "free"
            val linkStr = if (item.isPaid) "" else item.link.replace("'", "\\'")
            "{ id: ${item.id}, subject: '$escapedSubject', title: '$escapedTitle', desc: '$escapedDesc', type: '$typeStr', price: ${item.price}, link: '$linkStr' }"
        }

        return """<!DOCTYPE html>
<html lang='hi'>
<head>
  <meta charset='UTF-8'>
  <meta name='viewport' content='width=device-width, initial-scale=1.0'>
  <title>UP Board 12th Study Hub</title>
  <style>
    * { box-sizing: border-box; margin: 0; padding: 0; font-family: system-ui, -apple-system, sans-serif; }
    body { background: #f3f4f6; color: #1f2937; padding-bottom: 60px; }
    header { background: linear-gradient(135deg, #2563eb, #1d4ed8); color: #fff; padding: 18px 16px; position: sticky; top: 0; z-index: 10; box-shadow: 0 2px 8px rgba(0,0,0,0.12); }
    header h1 { font-size: 20px; font-weight: 700; }
    header p { font-size: 13px; opacity: 0.9; margin-top: 4px; }
    .container { max-width: 720px; margin: 0 auto; padding: 14px; }
    .search-box { width: 100%; padding: 12px 14px; border: 1px solid #d1d5db; border-radius: 10px; font-size: 15px; margin-bottom: 12px; outline: none; background: #fff; }
    .filters { display: flex; gap: 8px; overflow-x: auto; padding-bottom: 10px; margin-bottom: 12px; }
    .filter-btn { background: #fff; border: 1px solid #d1d5db; padding: 7px 14px; border-radius: 20px; font-size: 13px; font-weight: 600; cursor: pointer; white-space: nowrap; color: #374151; }
    .filter-btn.active { background: #2563eb; color: #fff; border-color: #2563eb; }
    .grid { display: grid; gap: 12px; }
    .card { background: #fff; border-radius: 12px; padding: 15px; box-shadow: 0 1px 4px rgba(0,0,0,0.08); border: 1px solid #e5e7eb; }
    .card-top { display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px; }
    .badge { font-size: 11px; font-weight: 700; padding: 4px 8px; border-radius: 6px; background: #dbeafe; color: #1e40af; }
    .tag-free { background: #dcfce7; color: #166534; }
    .tag-paid { background: #fef3c7; color: #92400e; }
    .card h3 { font-size: 16px; margin-bottom: 6px; color: #111827; }
    .card p { font-size: 13px; color: #6b7280; margin-bottom: 12px; line-height: 1.4; }
    .btn { display: block; width: 100%; text-align: center; padding: 10px; border-radius: 8px; font-size: 14px; font-weight: 600; text-decoration: none; cursor: pointer; border: none; }
    .btn-free { background: #16a34a; color: #fff; }
    .btn-paid { background: #ea580c; color: #fff; }
    .modal { display: none; position: fixed; inset: 0; background: rgba(0,0,0,0.6); z-index: 50; align-items: center; justify-content: center; padding: 16px; }
    .modal-box { background: #fff; border-radius: 14px; padding: 20px; max-width: 400px; width: 100%; }
    .modal-box h2 { font-size: 18px; margin-bottom: 8px; }
    .modal-box p { font-size: 14px; color: #4b5563; margin-bottom: 10px; line-height: 1.5; }
    .upi-box { background: #f3f4f6; padding: 10px; border-radius: 8px; font-weight: 700; text-align: center; margin-bottom: 12px; font-size: 15px; border: 1px dashed #9ca3af; }
    .btn-wa { background: #25D366; color: #fff; margin-bottom: 8px; }
    .btn-close { background: #e5e7eb; color: #1f2937; }
    .empty { text-align: center; color: #6b7280; padding: 30px 10px; font-size: 14px; }
  </style>
</head>
<body>
  <header>
    <h1>UP Board 12th Study Hub</h1>
    <p>Sabhi Subjects ke Notes, Model Papers & Important PDFs</p>
  </header>

  <div class='container'>
    <input type='text' id='searchInput' class='search-box' placeholder='Subject ya chapter search karein...'>
    <div class='filters' id='filterContainer'></div>
    <div class='grid' id='pdfList'></div>
  </div>

  <div class='modal' id='payModal'>
    <div class='modal-box'>
      <h2 id='modalTitle'>Notes Unlock Karein</h2>
      <p id='modalDesc'></p>
      <div class='upi-box' id='upiDisplay'></div>
      <p>1. Upar diye gaye UPI ID par payment karein.<br>2. Payment screenshot niche WhatsApp par bhejein.<br>3. Aapko turant PDF link mil jayega!</p>
      <a id='waBtn' href='#' target='_blank' class='btn btn-wa'>WhatsApp Par Screenshot Bhejein</a>
      <button id='closeBtn' class='btn btn-close'>Band Karein</button>
    </div>
  </div>

  <script>
    const MY_WHATSAPP = '${whatsappNumber.replace("'", "")}';
    const MY_UPI = '${upiId.replace("'", "")}';

    const pdfData = [
      $pdfArrayJs
    ];

    const subjects = ['All', 'Hindi', 'English', 'Physics', 'Chemistry', 'Maths', 'Biology', 'Arts/Commerce'];
    let activeSubject = 'All';

    document.getElementById('upiDisplay').textContent = 'UPI ID: ' + MY_UPI;

    function renderFilters() {
      const container = document.getElementById('filterContainer');
      container.innerHTML = '';
      subjects.forEach(sub => {
        const btn = document.createElement('button');
        btn.className = 'filter-btn' + (sub === activeSubject ? ' active' : '');
        btn.textContent = sub;
        btn.onclick = () => { activeSubject = sub; renderFilters(); renderPDFs(); };
        container.appendChild(btn);
      });
    }

    function renderPDFs() {
      const q = document.getElementById('searchInput').value.toLowerCase();
      const list = document.getElementById('pdfList');
      list.innerHTML = '';
      const filtered = pdfData.filter(item =>
        (activeSubject === 'All' || item.subject === activeSubject) &&
        (item.title.toLowerCase().includes(q) || item.desc.toLowerCase().includes(q) || item.subject.toLowerCase().includes(q))
      );
      if (filtered.length === 0) {
        const emptyDiv = document.createElement('div');
        emptyDiv.className = 'empty';
        emptyDiv.textContent = 'Koi PDF nahi mila. Dusra subject ya keyword try karein.';
        list.appendChild(emptyDiv);
        return;
      }
      filtered.forEach(item => {
        const card = document.createElement('div');
        card.className = 'card';
        const top = document.createElement('div');
        top.className = 'card-top';
        const subBadge = document.createElement('span');
        subBadge.className = 'badge';
        subBadge.textContent = item.subject;
        const typeBadge = document.createElement('span');
        typeBadge.className = 'badge ' + (item.type === 'free' ? 'tag-free' : 'tag-paid');
        typeBadge.textContent = item.type === 'free' ? 'FREE' : 'RS ' + item.price;
        top.appendChild(subBadge);
        top.appendChild(typeBadge);

        const h3 = document.createElement('h3');
        h3.textContent = item.title;
        const p = document.createElement('p');
        p.textContent = item.desc;

        card.appendChild(top);
        card.appendChild(h3);
        card.appendChild(p);

        if (item.type === 'free') {
          const a = document.createElement('a');
          a.className = 'btn btn-free';
          a.href = item.link;
          a.target = '_blank';
          a.textContent = 'Free PDF Download Karein';
          card.appendChild(a);
        } else {
          const btn = document.createElement('button');
          btn.className = 'btn btn-paid';
          btn.textContent = 'Unlock Notes (Rs ' + item.price + ')';
          btn.onclick = () => openModal(item);
          card.appendChild(btn);
        }
        list.appendChild(card);
      });
    }

    function openModal(item) {
      document.getElementById('modalTitle').textContent = item.title;
      document.getElementById('modalDesc').textContent = 'Price: Rs ' + item.price + ' (Lifetime access PDF)';
      const msg = encodeURIComponent('Hello! Mujhe ' + item.title + ' (Rs ' + item.price + ') chahiye. Maine payment kar di hai.');
      document.getElementById('waBtn').href = 'https://wa.me/' + MY_WHATSAPP + '?text=' + msg;
      document.getElementById('payModal').style.display = 'flex';
    }

    document.getElementById('closeBtn').onclick = () => {
      document.getElementById('payModal').style.display = 'none';
    };
    document.getElementById('searchInput').addEventListener('input', renderPDFs);

    renderFilters();
    renderPDFs();
  </script>
</body>
</html>"""
    }
}
