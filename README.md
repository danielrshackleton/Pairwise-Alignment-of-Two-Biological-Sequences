# Pairwise-Alignment-of-Two-Biological-Sequences
Pairwise Sequence Alignment is used to identify regions of similarity that may indicate functional, structural and/or evolutionary relationships between two biological sequences. This Java MVC application optimally aligns two protein sequences by similarity, using the Needleman-Wunsch Algorithm.

### Built With
- Java
- JavaFX

# Getting Started

### Prerequisites
This application requires Java Runtime Environment 1.8 or higher.

Download the latest JRE from: https://www.java.com/en/download/


### Installation
1. Clone the repo

`git clone https://github.com/danielrshackleton/Pairwise-Alignment-of-Two-Biological-Sequences.git`

2. Run 'Protein Sequencing.exe'


# Usage
You can either enter your two protein sequence strings into a text area, or you can
browse for a FASTA-format .txt file. You can input one or both of either. For instance, Sequence 1 can be entered in the first text
area, and Sequence2 uploaded as a file. You can then run your alignment through through several filters, namely:

- Local alignment checkbox – this can be turned on or off (default off) so you can locally or globally align the two sequences.
- Blosum matrix choicebox – default Blosum 62, you can select between Blosum 45, 62, and 80.
- Affine gap checkbox – you can decide whether you want to use an affine gap in your alignment.
- Affine gap penalty choicebox – this sets the affine gap penalty if using, and its default
value is 2, although it can be set to 1, 2, 5 or 10.
- Gap penalty choicebox – users can choose from 1, 5, 8, 10, 20, and 50, although the
gap’s default is 8.

After entering two biological sequences, click the ‘start alignment’ button  to display the
sequence alignment, as well as the sequence score, percentage match, and the two proteins’
molecule names and species origins (if FASTA files were used).

An additional ‘how does this work’ button brings up a tutorial to teach the fundamental algorithm of
this program.

### DEMO with existing FASTA files:
1. Run `Protein Sequencing.exe`
2. For Protein 1, click Browse and open src > Resources > Human_CS_O75390_fasta.txt
3. For Protein 2, click Browse and open src > Resources > Yeast_CS_P00890_fasta.txt
4. Click 'Start Alignment' (Leave options as default for a standard comparison)
