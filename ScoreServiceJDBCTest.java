* {
    box-sizing: border-box;
}

html {
    scroll-behavior: smooth;
}

body {
    margin: 0;
    background: #000;
    color: #dbe7ff;
    font-family: "Consolas", "Courier New", monospace;
    overflow-y: hidden;
}

/*BACKGROUND */
.background {
    position: relative;
    min-height: 100vh;
    width: 100%;
    background-size: cover;
    background-position: center bottom;
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-color: #000;
    overflow-x: hidden;
}

.background-rating {
    background-image: url("../images/rating-screen.png");
}

.background-main {
    background-image: url("../images/main.png");
}
.game-background {
    background-image: url('../images/level.png');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}
/*TITLE*/

.title-block {
    position: relative;
    top: 40px;
    left: 50%;
    transform: translateX(-50%);
    text-align: center;
    z-index: 3;
}

.title {
    margin: 0;

    font-family: "Consolas", "Menlo", "Monaco", "Courier New", monospace;

    font-size: 92px;
    font-weight: 300;
    letter-spacing: 10px;
    line-height: 1;
    color: #e6eeff;
    text-shadow:
            0 0 4px rgba(120, 170, 255, 0.08);
}

.title-line {
    width: 460px;
    max-width: 100%;
    height: 1px;
    margin: 12px auto 0;

    background:
            linear-gradient(90deg, transparent,
                    rgba(120, 170, 255, 0.35),
                    transparent);
}

.level-subtitle {
    margin-top: 16px;
    color:
            rgba(120, 160, 255, 0.55);
    font-size: 12px;
    letter-spacing: 8px;
    text-transform: uppercase;
    text-align: center;
    opacity: 0.65;
    text-shadow:
            0 0 14px rgba(80, 140, 255, 0.12);
}

/*LEVEL LAYOUT */
.level-layout {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 22px;
    margin-top: 70px;
    width: 100%;
    max-width: 1650px;
    margin-left: auto;
    margin-right: auto;
    padding: 0 20px;
}

.side-column {
    width: 340px;
    flex-shrink: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}

.side-left {
    gap: 34px;
}

/*UI CARDS*/

.ui-card {
    width: 165px;

    padding: 28px 20px;

    border-radius: 20px;

    text-align: center;

    background:
            rgba(5, 12, 28, 0.62);

    backdrop-filter: blur(18px);

    border:
            1px solid rgba(90, 140, 255, 0.18);
    box-shadow:
            0 0 35px rgba(0, 90, 255, 0.14),
            inset 0 0 14px rgba(0, 120, 255, 0.05);
}

.ui-icon {
    font-size: 34px;
    color: #9dcbff;
    margin-bottom: 18px;
    text-shadow:
            0 0 22px rgba(80, 140, 255, 0.45);
}

.ui-label {
    font-size: 15px;
    letter-spacing: 6px;
    color: #7eb6ff;
    margin-bottom: 12px;
}

.ui-value {
    font-size: 34px;
    color: #edf4ff;
    text-shadow:
            0 0 16px rgba(120, 170, 255, 0.18);
}

/*BASE LORE CARD*/
.lore-card {
    width: 300px;
    min-height: 430px;
    padding: 34px 30px;
    text-align: left;
    border-radius: 20px;
    box-shadow:
            0 0 50px rgba(0, 110, 255, 0.16),
            inset 0 0 22px rgba(0, 120, 255, 0.06);
}

/*LEVEL 1*/

.level-1 .lore-card {
    width: 340px;
    min-height: 470px;
}

/*LEVEL 2*/

.level-2 .lore-card {
    width: 310px;
    min-height: 430px;
}

/*LEVEL 3*/

.level-3 .lore-card {
    width: 270px;
    min-height: 390px;
}

/*LEVEL 4*/

.level-4 .lore-card {
    width: 230px;
    min-height: 340px;
}



.lore-title {
    margin-bottom: 24px;
    color: #8fc6ff;
    font-size: 16px;
    letter-spacing: 7px;
    text-align: center;
    text-shadow:
            0 0 16px rgba(80, 140, 255, 0.24);
}

.lore-text {
    color: #dbe7ff;
    font-size: 15px;
    line-height: 2.1;
    letter-spacing: 1.5px;
    opacity: 0.92;
}

/*DATA CONTAINER*/

.data-container {
    position: relative;
    margin: 40px auto;
    padding: 30px;
    border-radius: 15px;
    background:
            rgba(5, 10, 20, 0.7);
    backdrop-filter: blur(15px);
    border:
            1px solid rgba(68, 98, 160, 0.3);

    width: fit-content;
    max-width: 95vw;
    min-width: 300px;
    display: flex;
    flex-direction: column;
    align-items: center;
    box-shadow:
            0 0 50px rgba(0, 0, 0, 0.8);
}

.level-container {
    margin-top: 0 !important;
}

/*GAME WRAPPER*/

.game-wrapper {
    padding: 18px;

    border-radius: 28px;

    background:
            rgba(8, 16, 35, 0.45);

    backdrop-filter: blur(18px);

    border:
            1px solid rgba(100, 150, 255, 0.1);

    box-shadow:
            0 0 60px rgba(0, 90, 255, 0.12),
            inset 0 0 20px rgba(0, 90, 255, 0.05);
}

.game-inner {
    padding: 32px;
    border-radius: 22px;
    background:
            rgba(4, 12, 26, 0.78);
    border:
            1px solid rgba(100, 150, 255, 0.08);
    box-shadow:
            inset 0 0 25px rgba(0, 120, 255, 0.08);
}

/*GAME BOARD*/
.game-board {
    display: inline-grid !important;
    gap: 14px;
    justify-content: center;
    margin: 10px 0 28px;
    padding: 10px;
    overflow: visible;
    max-height: unset;
}

/*TILES*/

.tile {
    display: flex !important;

    width: 58px !important;
    height: 58px !important;
    align-items: center;
    justify-content: center;
    border-radius: 10px;
    background:
            rgba(20, 30, 50, 0.55);
    border:
            1px solid rgba(120, 160, 255, 0.18);

    color: #e6f0ff;
    font-size: 18px;
    text-decoration: none;
    transition: 0.2s ease;
    box-shadow:
            inset 0 0 10px rgba(0, 120, 255, 0.08);
}

.tile:hover {
    transform: translateY(-2px);
    box-shadow:
            0 0 15px rgba(80, 140, 255, 0.35),
            inset 0 0 12px rgba(120, 180, 255, 0.2);
}

/* Empty cell keeps board shape */
.tile-empty {
    width: 58px;
    height: 58px;
}

.tile.selected {
    background:
            rgba(61, 143, 255, 0.3) !important;

    border:
            1px solid rgba(120, 180, 255, 0.4) !important;

    box-shadow:
            0 0 18px rgba(80, 140, 255, 0.35);
}

/* Possible move tile */
.tile.possible-move {
    border:
            1px solid rgba(143, 198, 255, 0.55);

    background:
            rgba(45, 82, 135, 0.38);

    box-shadow:
            0 0 14px rgba(80, 140, 255, 0.28),
            inset 0 0 12px rgba(120, 180, 255, 0.14);

    animation: hintPulse 1.5s ease-in-out infinite;
}

/* Highlight hints*/
.tile-empty.path-hint {
    border-radius: 10px;
    border:
            1px dashed rgba(143, 198, 255, 0.18);

    background:
            rgba(31, 57, 105, 0.08);

    box-shadow:
            inset 0 0 12px rgba(80, 140, 255, 0.08);
}

@keyframes hintPulse {
    0%, 100% {
        transform: scale(1);
    }
    50% {
        transform: scale(1.03);
    }
}

/*CONTROLS*/

.controls {
    display: flex;

    justify-content: center;

    gap: 14px;
}

.controls .menu-item {
    padding: 12px 26px;

    border-radius: 12px;

    font-size: 14px;

    letter-spacing: 3px;

    word-spacing: normal;

    border:
            1px solid rgba(120, 170, 255, 0.15);

    background:
            rgba(20, 30, 50, 0.65);

    transition: 0.25s;
}

.controls .menu-item:hover {
    transform: translateY(-2px);

    box-shadow:
            0 0 18px rgba(80, 140, 255, 0.35);
}

/*MAIN MENU*/

.menu-panel {
    position: absolute;

    top: 540px;
    left: 50%;

    transform: translateX(-50%);

    width: 320px;

    padding: 10px 0;

    border-radius: 18px;

    background:
            rgba(5, 10, 20, 0.42);

    backdrop-filter: blur(14px);

    border:
            1px solid rgba(68, 98, 160, 0.22);

    box-shadow:
            0 0 30px rgba(0, 80, 255, 0.08);
}

.menu-panel::before,
.menu-panel::after {
    content: "";

    position: absolute;

    width: 16px;
    height: 16px;

    top: 14px;

    border-top:
            2px solid #9d2235;
}

.menu-panel::before {
    left: 14px;

    border-left:
            2px solid #9d2235;
}

.menu-panel::after {
    right: 14px;

    border-right:
            2px solid #9d2235;
}

.menu-item {
    display: block;

    padding: 18px 16px;

    text-align: center;

    color: #d8e3ff;

    text-decoration: none;

    font-size: 22px;

    letter-spacing: 6px;

    border-bottom:
            1px solid rgba(65, 89, 138, 0.16);

    transition: 0.25s ease;
}

.menu-item:last-child {
    border-bottom: none;
}

.menu-item:hover {
    color: #8fc6ff;

    text-shadow:
            0 0 8px rgba(80, 140, 255, 0.9);

    background:
            rgba(31, 57, 105, 0.12);
}

/*LEVEL 1*/

.level-1 .level-layout {
    gap: 10px;
}

.level-1 .lore-card {
    transform: translateX(-35px);
}

.level-1 .side-column:first-child {
    transform: translateX(35px);
}
/*LEVEL 2*/

.level-2 .tile {
    width: 38px !important;
    height: 38px !important;

    font-size: 17px;
}

.level-2 .tile-empty {
    width: 38px;
    height: 38px;
}

.level-2 .game-board {
    gap: 12px;
}
/*LEVEL 3*/

.level-3 .game-board {
    gap: 5px;
}

.level-3 .tile {
    width: 38px !important;
    height: 38px !important;

    font-size: 13px;

    border-radius: 8px;
}

.level-3 .tile-empty {
    width: 38px;
    height: 38px;
}
/*LEVEL 4*/
.level-4 .game-board {
    gap: 5px;
}

.level-4 .tile {
    width: 38px !important;
    height: 38px !important;
    font-size: 13px;
    border-radius: 8px;
}

.level-4 .tile-empty {
    width: 38px;
    height: 38px;
}

/* LEVEL 5*/

.level-5 .game-board {
    gap: 5px;
}

.level-5 .tile {
    width: 38px !important;
    height: 38px !important;

    font-size: 13px;

    border-radius: 8px;
}

.level-5 .tile-empty {
    width: 38px;
    height: 38px;
}

/* Extra compact layout for the largest board */

.level-5 .side-column {
    width: 240px;
}

.level-5 .lore-card {
    width: 220px;
}

.level-5 .data-container {
    max-width: 980px;
}

/*WARNING OVERLAY FIXED */
.warning-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;

    background:
            rgba(0, 0, 0, 0.78);
    z-index: 99999;
}

.warning-popup {
    width: 420px;
    padding: 40px;
    border-radius: 24px;
    text-align: center;
    background:
            rgba(6, 12, 24, 0.96);
    border:
            1px solid rgba(255, 90, 90, 0.22);
    box-shadow:
            0 0 60px rgba(255, 70, 70, 0.12),
            inset 0 0 20px rgba(255, 70, 70, 0.05);
}

.warning-title {
    margin-bottom: 24px;
    color:
            rgba(255, 120, 120, 0.92);

    font-size: 24px;
    letter-spacing: 10px;
    text-shadow:
            0 0 18px rgba(255, 70, 70, 0.2);
}

.warning-message {
    color: #edf4ff;
    font-size: 18px;
    line-height: 2;
    letter-spacing: 4px;
}

.warning-button {
    margin-top: 28px;
    padding: 14px 26px;
    border:
            1px solid rgba(120, 170, 255, 0.2);

    border-radius: 12px;
    background:
            rgba(18, 28, 50, 0.8);
    color: #dbe7ff;
    font-family: inherit;
    font-size: 13px;
    letter-spacing: 4px;
    cursor: pointer;
    transition: 0.25s;
}

.warning-button:hover {
    transform: translateY(-2px);
    box-shadow:
            0 0 16px rgba(80, 140, 255, 0.35);
}

/*MOBILE*/
@media (max-width: 900px) {

    .title {
        font-size: 48px;

        letter-spacing: 10px;
    }

    .level-layout {
        flex-direction: column;

        gap: 20px;

        margin-top: 40px;
    }

    .side-column {
        width: 100%;
    }

    .tile {
        width: 44px !important;
        height: 44px !important;

        font-size: 15px;
    }

    .game-board {
        gap: 8px;
    }

    .lore-card {
        width: 100%;
    }
}

/*COMMUNITY / PROFILE */

.background-ekran {
    background-image: url("../images/ekran.png");
}

/* Top bar buttons */
.auth-bar {
    position: fixed;
    top: 18px;
    right: 22px;
    z-index: 20;

    display: flex;
    align-items: center;
    justify-content: flex-end;
    gap: 14px;

    min-height: 42px;
    padding: 9px 14px;

    border: 1px solid rgba(120, 170, 255, 0.16);
    border-radius: 12px;

    background: rgba(5, 12, 28, 0.6);
    backdrop-filter: blur(16px);

    box-shadow:
            0 0 24px rgba(0, 90, 255, 0.12),
            inset 0 0 14px rgba(0, 120, 255, 0.04);
}

.auth-status {
    display: flex;
    align-items: center;
    gap: 8px;
}

.auth-status-label,
.stat-caption,
.field-label,
.activity-heading {
    color: #5f90d4;
    font-size: 11px;
    letter-spacing: 3px;
}

.auth-user,
.auth-link {
    color: #dbe7ff;
    text-decoration: none;
    letter-spacing: 2px;
}

.auth-user {
    color: #9dcbff;
    text-shadow: 0 0 12px rgba(80, 140, 255, 0.28);
}

.auth-actions {
    display: flex;
    align-items: center;
    gap: 12px;
}

.inline-form {
    display: inline;
    margin: 0;
}

.auth-link {
    border: 0;
    background: transparent;
    font-family: inherit;
    font-size: 12px;
    line-height: 1;
    padding: 4px 0;
    cursor: pointer;
    transition: 0.25s;
}

.auth-link:hover,
.auth-mini-link:hover {
    color: #8fc6ff;
    text-shadow: 0 0 10px rgba(80, 140, 255, 0.8);
}

.auth-link-button {
    padding: 0;
}

.auth-title-block {
    top: 90px;
}

.auth-title {
    font-size: 68px;
}

.auth-container {
    margin-top: 150px;
    width: 430px;
}

.auth-card {
    width: 100%;
}

.auth-card-title {
    margin-bottom: 22px;
    color: #8fc6ff;
    font-size: 16px;
    letter-spacing: 6px;
    text-align: center;
    text-shadow: 0 0 16px rgba(80, 140, 255, 0.24);
}

.auth-form {
    display: flex;
    flex-direction: column;
    gap: 9px;
}

.comm-input {
    width: 100%;
    padding: 11px 12px;

    background: rgba(0, 0, 0, 0.32);
    border: 1px solid rgba(143, 198, 255, 0.2);
    border-radius: 8px;

    color: #e6eeff;
    font-family: "Consolas", "Courier New", monospace;
    font-size: 14px;
    outline: none;

    transition: 0.25s;
}

.comm-input:focus {
    border-color: rgba(143, 198, 255, 0.5);
    box-shadow: 0 0 14px rgba(61, 143, 255, 0.22);
}

.auth-input {
    margin-bottom: 6px;
}

.remember-row {
    display: flex;
    align-items: center;
    gap: 10px;
    margin: 8px 0;

    color: #8fb8ef;
    font-size: 12px;
    letter-spacing: 2px;
}

.post-button {
    display: block;
    width: 100%;

    margin-top: 10px;
    padding: 13px;

    background: rgba(31, 57, 105, 0.2);
    border: 1px solid rgba(143, 198, 255, 0.2);
    border-radius: 8px;

    color: #d8e3ff;
    font-family: "Consolas", "Courier New", monospace;
    font-size: 18px;
    letter-spacing: 4px;
    text-align: center;
    text-decoration: none;

    cursor: pointer;
    transition: 0.3s ease;
}

.post-button:hover {
    color: #8fc6ff;
    text-shadow: 0 0 10px #3d8fff;
    background: rgba(31, 57, 105, 0.4);
    box-shadow: 0 0 15px rgba(61, 143, 255, 0.3);
}

.auth-secondary {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 10px;
    margin-top: 16px;
}

.auth-mini-link {
    padding: 12px 8px;
    border: 1px solid rgba(120, 170, 255, 0.12);
    border-radius: 8px;
    font-size: 12px;
    letter-spacing: 2px;
}

.ai-warning,
.ai-success {
    margin-bottom: 14px;
    padding: 12px;
    border-radius: 8px;
    font-size: 12px;
    letter-spacing: 2px;
    line-height: 1.6;
}

.ai-warning {
    color: rgba(255, 150, 150, 0.94);
    border: 1px solid rgba(255, 90, 90, 0.22);
    background: rgba(60, 8, 18, 0.32);
    box-shadow: inset 0 0 16px rgba(255, 70, 70, 0.04);
}

.ai-warning span {
    display: block;
}

.ai-success {
    color: #a9d4ff;
    border: 1px solid rgba(120, 170, 255, 0.2);
    background: rgba(12, 40, 70, 0.28);
}

.community-panel {
    position: absolute;
    top: 500px;
    left: 50%;
    transform: translateX(-50%);
    width: 550px;
    padding: 30px;
    background: rgba(5, 10, 20, 0.45);
    backdrop-filter: blur(15px);
    border: 1px solid rgba(68, 98, 160, 0.25);
    border-radius: 12px;
    box-shadow: 0 0 40px rgba(0, 0, 0, 0.7);
}

.community-panel::before,
.community-panel::after {
    content: "";
    position: absolute;
    width: 18px;
    height: 18px;
    top: 15px;
    border-top: 3px solid #9d2235;
}

.community-panel::before {
    left: 15px;
    border-left: 3px solid #9d2235;
}

.community-panel::after {
    right: 15px;
    border-right: 3px solid #9d2235;
}

.stats-header {
    display: flex;
    justify-content: space-between;
    align-items: baseline;
    margin-bottom: 20px;
    padding-bottom: 15px;
    border-bottom: 1px solid rgba(143, 198, 255, 0.1);
}

.stat-right {
    text-align: right;
}

.review-count {
    font-size: 18px;
    color: #8fc6ff;
}

.community-form-row {
    display: flex;
    gap: 10px;
}

.community-textarea {
    height: 62px;
    resize: none;
    margin-top: 8px;
}

.anonymous-panel {
    margin: 10px 0 18px;
}

.auth-panel-link {
    font-size: 14px;
}

.reviews-scroll {
    max-height: 190px;
    overflow-y: auto;
    margin: 20px 0;
    padding-right: 10px;
}

.reviews-scroll::-webkit-scrollbar {
    width: 2px;
}

.reviews-scroll::-webkit-scrollbar-thumb {
    background: rgba(143, 198, 255, 0.2);
}

.review-item {
    padding: 10px 0;
    border-bottom: 1px solid rgba(143, 198, 255, 0.05);
}

.review-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 12px;
}

.review-user {
    color: #8fc6ff;
    font-size: 18px;
    letter-spacing: 2px;
}

.review-stars {
    color: #ffcc66;
    font-size: 14px;
}

.review-text {
    margin-top: 5px;
    color: #dbe7ff;
    font-size: 15px;
    opacity: 0.82;
}

.community-back {
    text-align: center;
    margin-top: 10px;
}

.community-back-link {
    border: none;
    font-size: 16px;
    padding: 10px;
}

.leaderboard-container {
    min-width: 680px;
    margin-top: 90px;
}

.profile-title-block {
    top: 64px;
}

.profile-layout {
    display: grid;
    grid-template-columns: minmax(360px, 700px) minmax(320px, 520px);
    gap: 22px;
    justify-content: center;
    align-items: start;
    margin: 130px auto 0;
    padding: 0 24px 40px;
    max-width: 1280px;
}

.profile-panel {
    width: 100%;
    margin: 0;
}

.profile-grid {
    display: grid;
    grid-template-columns: repeat(4, minmax(120px, 1fr));
    gap: 14px;
    width: 100%;
}

.profile-stat {
    width: 100%;
    min-height: 130px;
    border-radius: 12px;
}

.profile-stat .ui-label {
    letter-spacing: 3px;
}

.profile-meta {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 14px;
    width: 100%;
    margin-top: 18px;
}

.profile-meta div,
.activity-row,
.activity-empty {
    padding: 14px;
    border: 1px solid rgba(143, 198, 255, 0.1);
    border-radius: 8px;
    background: rgba(5, 12, 28, 0.45);
}

.profile-meta span,
.activity-empty {
    color: #5f90d4;
    font-size: 11px;
    letter-spacing: 3px;
}

.profile-meta strong {
    display: block;
    margin-top: 8px;
    color: #dbe7ff;
    font-size: 14px;
    letter-spacing: 1px;
}

.activity-panel {
    align-items: stretch;
}

.activity-section {
    margin-bottom: 18px;
}

.activity-heading {
    margin-bottom: 10px;
}

.activity-row {
    display: flex;
    justify-content: space-between;
    gap: 14px;
    margin-bottom: 8px;
    color: #dbe7ff;
    font-size: 13px;
    letter-spacing: 1px;
}

.activity-comment span:first-child {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

/* Level select background */
.background-levels {
    background-image: url("../images/levels.png");
    background-position: center 25%;
    background-size: cover;
}

/* Level select layout */
.levels-title-block {
    top: 50px;
}

.levels-title {
    font-size: 70px;
}

.levels-title-line {
    width: 280px;
}

.levels-panel {
    position: absolute;
    top: 75%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 430px;
    padding: 0;
    background: rgba(5, 10, 20, 0.5);
    border: 1px solid rgba(143, 198, 255, 0.1);
    border-radius: 10px;
}

.levels-container {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 15px;
    padding: 45px 10px 35px;
}

.back-wrapper {
    border-top: 1px solid rgba(143, 198, 255, 0.1);
}

.back-item {
    display: block;
    padding: 20px;
    text-align: center;
    color: #d8e3ff;
    text-decoration: none;
    font-size: 22px;
    letter-spacing: 8px;
    transition: 0.3s;
}

.back-item:hover {
    color: #fff;
    background: rgba(143, 198, 255, 0.08);
    text-shadow: 0 0 15px #3d8fff;
}

/* Locked levels stay visible but inactive */
.level-tile {
    position: relative;
}

.level-tile.completed {
    border-color: rgba(143, 198, 255, 0.38);
    box-shadow:
            0 0 16px rgba(80, 140, 255, 0.22),
            inset 0 0 12px rgba(120, 180, 255, 0.12);
}

.level-tile.locked {
    opacity: 0.38;
    filter: grayscale(0.8);
    cursor: not-allowed;
    pointer-events: none;
    background: rgba(10, 15, 24, 0.7);
}

.level-tile.completed.locked {
    opacity: 0.72;
}

.lock-icon {
    position: absolute;
    right: 5px;
    bottom: 4px;
    font-size: 10px;
    opacity: 0.78;
}

.level-warning {
    margin: 20px 20px 0;
}

.guest-progress-card {
    margin-top: 14px;
    padding: 20px 16px;
}

.guest-progress-text {
    color: rgba(255, 150, 150, 0.82);
    font-size: 11px;
    letter-spacing: 2px;
    line-height: 1.5;
}

/* Star rating display with half-star support */
.star-display {
    position: relative;
    display: inline-block;
    color: rgba(143, 198, 255, 0.16);
    letter-spacing: 3px;
    line-height: 1;
}

.star-fill {
    position: absolute;
    top: 0;
    left: 0;
    width: var(--rating-width);
    overflow: hidden;
    white-space: nowrap;
    color: #8fc6ff;
    text-shadow: 0 0 12px rgba(80, 140, 255, 0.42);
}

.average-stars {
    margin-top: 8px;
    font-size: 27px;
}

.mini-stars {
    font-size: 14px;
}

.rating-value {
    display: block;
    margin-top: 8px;
    color: #5f90d4;
    font-size: 12px;
    letter-spacing: 3px;
}

/* Interactive stars in comment form */
.star-picker {
    display: inline-flex;
    flex-direction: row-reverse;
    align-items: center;
    justify-content: center;
    gap: 4px;
    min-width: 170px;
    padding: 8px 10px;
    border: 1px solid rgba(143, 198, 255, 0.2);
    border-radius: 8px;
    background: rgba(0, 0, 0, 0.32);
}

.star-picker input {
    display: none;
}

.star-picker label {
    color: rgba(143, 198, 255, 0.2);
    font-size: 22px;
    cursor: pointer;
    transition: transform 0.2s ease, color 0.2s ease, text-shadow 0.2s ease;
}

.star-picker label:hover,
.star-picker label:hover ~ label,
.star-picker input:checked ~ label {
    color: #8fc6ff;
    text-shadow: 0 0 12px rgba(80, 140, 255, 0.55);
    transform: scale(1.08);
}

/* Top score polished list */
.leaderboard-inner {
    width: 100%;
    min-width: 760px;
}

.leaderboard-list {
    display: grid;
    gap: 10px;
    width: 100%;
}

.leaderboard-entry {
    display: grid;
    grid-template-columns: 58px minmax(180px, 1fr) 120px 110px 110px;
    align-items: center;
    gap: 12px;
    padding: 14px 16px;
    border: 1px solid rgba(143, 198, 255, 0.1);
    border-radius: 10px;
    background: rgba(5, 12, 28, 0.5);
    transition: 0.25s ease;
}

.leaderboard-entry:hover {
    transform: translateY(-2px);
    border-color: rgba(143, 198, 255, 0.24);
    box-shadow: 0 0 18px rgba(80, 140, 255, 0.16);
}

.leaderboard-entry.top-rank {
    background:
            linear-gradient(90deg, rgba(31, 57, 105, 0.34), rgba(5, 12, 28, 0.5));
}

.rank-badge {
    width: 38px;
    height: 38px;
    display: flex;
    align-items: center;
    justify-content: center;
    border: 1px solid rgba(143, 198, 255, 0.22);
    border-radius: 8px;
    color: #8fc6ff;
    font-size: 17px;
    text-shadow: 0 0 12px rgba(80, 140, 255, 0.36);
}

.player-name {
    display: block;
    color: #e6eeff;
    font-size: 18px;
    letter-spacing: 3px;
}

.player-meta {
    display: block;
    margin-top: 5px;
    color: #5f90d4;
    font-size: 11px;
    letter-spacing: 3px;
}

.leaderboard-score {
    text-align: right;
}

.leaderboard-score span {
    display: block;
    color: #dbe7ff;
    font-size: 22px;
}

.leaderboard-score small {
    color: #5f90d4;
    font-size: 10px;
    letter-spacing: 3px;
}

.muted-score span {
    font-size: 17px;
    opacity: 0.86;
}

.leaderboard-back {
    margin-top: 18px;
    border: 1px solid rgba(120, 170, 255, 0.12);
    border-radius: 8px;
}

.register-inline-link {
    display: inline-block;
    margin-top: 8px;
    color: #8fc6ff;
    text-decoration: none;
    letter-spacing: 3px;
}

/* Guest hint in main menu */
.guest-hint {
    padding: 12px 18px;
    border-bottom: 1px solid rgba(65, 89, 138, 0.16);
    color: rgba(255, 150, 150, 0.82);
    font-size: 10px;
    letter-spacing: 2px;
    line-height: 1.5;
    text-align: center;
}

/* Story style transition screen */
.transition-screen {
    background-image: url("../images/transition-screen.png");
    background-position: center;
    display: flex;
    align-items: center;
    justify-content: center;
}

/* Dialog window after level and ending */
.story-dialog-box {
    width: min(760px, calc(100vw - 32px));
    padding: 28px;
    border: 1px solid rgba(55, 97, 122, 0.78);
    border-radius: 10px;
    background: rgba(0, 0, 0, 0.82);
    box-shadow:
            0 0 35px rgba(0, 0, 0, 0.72),
            inset 0 0 18px rgba(80, 140, 255, 0.05);
}

.dialog-speaker {
    display: inline-block;
    margin-bottom: 18px;
    padding-bottom: 6px;
    border-bottom: 1px solid #37617a;
    color: #8fc6ff;
    font-size: 18px;
    letter-spacing: 4px;
}

.dialog-text {
    color: #edf4ff;
    font-size: 22px;
    line-height: 1.7;
    letter-spacing: 2px;
}

.dialog-stats {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    margin: 22px 0 14px;
}

.dialog-stats span {
    padding: 10px 12px;
    border: 1px solid rgba(143, 198, 255, 0.12);
    border-radius: 8px;
    background: rgba(5, 12, 28, 0.55);
    color: #5f90d4;
    font-size: 12px;
    letter-spacing: 2px;
}

.dialog-stats strong {
    color: #dbe7ff;
}

.dialog-button {
    margin-top: 18px;
}

.dialog-link {
    margin-top: 10px;
    border: none;
    font-size: 13px;
    letter-spacing: 3px;
}

@media (max-width: 900px) {
    body {
        overflow-y: auto;
    }

    .auth-bar {
        left: 12px;
        right: 12px;
        justify-content: space-between;
        gap: 8px;
    }

    .auth-container,
    .community-panel,
    .leaderboard-container {
        width: calc(100vw - 28px);
        min-width: unset;
    }

    .community-panel {
        top: 310px;
    }

    .auth-title {
        font-size: 44px;
    }

    .profile-layout {
        grid-template-columns: 1fr;
        margin-top: 100px;
    }

    .profile-grid {
        grid-template-columns: repeat(2, minmax(120px, 1fr));
    }

    .profile-meta {
        grid-template-columns: 1fr;
    }

}
