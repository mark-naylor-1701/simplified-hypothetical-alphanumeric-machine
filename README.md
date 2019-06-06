# simplified-hypothetical-alphanumeric-machine

A project dedicated to the memory of James Maginnis, a 30 year computer Science
professor at Drexel University.

Professor Maginnis created SHAM as the primary teaching tool for the Operating
Systems course. This course was required for Computer Science majors in the late
1980s.

The SHAM system consisted of two applications. The first was the SHAM computer.
On start-up, this application would read contents a file, "sham.rom". sham.rom
consisted of machine code that was read into "RAM", registers initialized, and
execution machine code started. The rom file was the kernel of the operating
system. The second application was the assembler. Professor Maginnis created his
own machine code and assembly language. The assembler, as expected, read in the
source code file and generated (primarily) the sham.rom.

The SHAM computer and assembly language were uniquely crafted to address the
fundamentals in course that lasted 10 weeks. (Drexel has 4 terms per academic
year. Each term consists of 11 weeks; 10 weeks of instruction, 1 week of
finals.) The SHAM computer lacked many capabilities of modern CPUs: no floating
point operations, integer (short) math only, no mod operator, no bit operators.
However, it did include operators that don't exist on any chip. Opcodes were
included that could: read character from keyboard, write character to screen,
stream 256 bytes to/from a file.

The SHAM system was written to run on the original Macintosh® platform.
(https://en.wikipedia.org/wiki/Macintosh_128K) Tat platform ran at 7.8336 MHz.
Granted, that was 30+ years ago, but I recall that both the computer and
assembler ran quickly enough to be comfortable to use. Less than a minute for
the assembler. Can't swear to the language he used for both, but I'm reasonably
sure he used C. The SHAM user manual included < 20 SLOC of C to give us insight
into the "host" computer.

As an undergrad student, I was impressed by the computer and assembler. Another
anecdote cements that in my mind. My fellow students and I were probably using
the basic text editor that came on the Mac OS floppies. Some may have gotten
hold of a different one, as we were discussing the merits of the options. In one
class, we asked Professor Maginnis what editor he used. In an offhand manner, he
told us that he had written his own. That fits well with his creation of SHAM.

March of this year (2019) I found the handouts and notes from my OS class. The
discovery ignited my "scratch an itch" that is so common in the open source
world. As much as I respect C, I'm out of practice and did not want to research
standard libraries, research other libraries, or deal with low level details. I
had some professional experience with Scala; originally chose that language for
implementation. But, even though Scala is a better Java, it sometimes requires
explicit declarations and conversions. I decided to implement in Clojure. I'm
one of those to whom Lisp has an appeal. It suits my way of thinking. It meshes
well with my editor of choice: emacs. I've extended my emacs considerably,
making it my personal writing environment. As emacs consists of its own brand of
Lisp, I've had to learn key concepts to code my extensions. Also, I've taken
this quote, from Eric S. Raymond (How To Be a Hacker;
http://www.catb.org/~esr/faqs/hacker-howto.html) to heart.

    "LISP is worth learning for a different reason — the profound
    enlightenment experience you will have when you finally get it.
    That experience will make you a better programmer for the rest of
    your days, even if you never actually use LISP itself a lot. (You
    can get some beginning experience with LISP fairly easily by
    writing and modifying editing modes for the Emacs text editor, or
    Script-Fu plugins for the GIMP.)"
